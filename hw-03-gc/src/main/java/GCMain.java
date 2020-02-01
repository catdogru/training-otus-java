import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.sun.management.GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION;
import static java.lang.System.currentTimeMillis;
import static java.lang.management.ManagementFactory.getGarbageCollectorMXBeans;

/*
-Xms64m -Xmx64m

-XX:+UseSerialGC:           totalTime: 289sec | minor: {count=4, duration=172ms} | major: {count=3, duration=941ms} | listSize: 796354
-XX:+UseParallelGC:         totalTime: 278sec | minor: {count=3, duration=166ms} | major: {count=37, duration=7418ms} | listSize: 789319
-XX:+UseConcMarkSweepGC:    totalTime: 297sec | minor: {count=10, duration=287ms} | major: {count=109, duration=52977ms} | listSize: 778055
-XX:+UseG1GC:               totalTime: 279sec | minor: {count=24, duration=574ms} | major: {count=2, duration=254ms} | listSize: 810325

 */

public class GCMain {
    private enum GcActionType {MINOR, MAJOR}

    private static long beginTime = currentTimeMillis();
    private static List<FatObject> list = new ArrayList<>();
    private static Map<GcActionType, GcActionStats> statsMap = Map.of(
            GcActionType.MAJOR, new GcActionStats(),
            GcActionType.MINOR, new GcActionStats());

    public static void main(String... args) throws InterruptedException {
        startGcMonitoring();
        getOOM();
    }

    private static void getOOM() throws InterruptedException {
        while (true) {
            for (int i = 0; i <= 3; i++) {
                list.add(new FatObject());
            }
            Thread.sleep(1);
            list.remove(0);
        }
    }

    private static void startGcMonitoring() {
        //get all the GarbageCollectorMXBeans - there's one for each heap generation
        //so probably two - the old generation and young generation
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = getGarbageCollectorMXBeans();

        //Install a notifcation handler for each bean
        for (GarbageCollectorMXBean gcBean : garbageCollectorMXBeans) {
            //System.out.println(gcBean.getName());
            NotificationEmitter emitter = (NotificationEmitter) gcBean;
            //use an anonymously generated listener for this example
            // - proper code should really use a named   class
            //implement the notifier callback handler
            NotificationListener listener = (notification, handback) -> {
                //we only handle GARBAGE_COLLECTION_NOTIFICATION notifications here
                if (notification.getType().equals(GARBAGE_COLLECTION_NOTIFICATION)) {
                    //get the information associated with this notification
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    long duration = info.getGcInfo().getDuration();
                    GcActionType gcActionType = getGcActionType(info);

                    statsMap.get(gcActionType)
                            .incCount()
                            .addDuration(duration);

                    long totalTime = (currentTimeMillis() - beginTime) / 1000;
                    System.out.println(
                            "totalTime: " + totalTime + "sec" + " |"
                                    + " minor: " + statsMap.get(GcActionType.MINOR).toString() + " |"
                                    + " major: " + statsMap.get(GcActionType.MAJOR).toString() + " |"
                                    + " listSize: " + list.size());
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }
    }

    private static GcActionType getGcActionType(GarbageCollectionNotificationInfo info) {
        if ("end of minor GC".equals(info.getGcAction())) {
            return GcActionType.MINOR;
        }
        return GcActionType.MAJOR;
    }
}
