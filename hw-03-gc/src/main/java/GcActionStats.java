public class GcActionStats {
    int count;
    long duration;

    public GcActionStats incCount() {
        this.count++;
        return this;
    }

    public GcActionStats addDuration(long duration) {
        this.duration += duration;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "count=" + count +
                ", duration=" + duration + "ms}";
    }
}
