import annotation.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class IoC {
    public static <I, P extends I> I createProxy(P proxiedObject) {
        InvocationHandler handler = new LoggingInvocationHandler(proxiedObject, Log.class);
        return (I) Proxy.newProxyInstance(IoC.class.getClassLoader(), proxiedObject.getClass().getInterfaces(), handler);
    }

    static class LoggingInvocationHandler implements InvocationHandler {
        private final Object proxiedClass;
        private List<Method> annotatedMethodList;

        LoggingInvocationHandler(Object proxiedClass, Class<? extends Annotation> annotation) {
            this.proxiedClass = proxiedClass;
            this.annotatedMethodList = getAnnotatedMethods(annotation);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (Method annotatedMethod : annotatedMethodList) {
                if (!annotatedMethod.getName().equals(method.getName())) {
                    continue;
                }
                doLogging(method, args);
                break;
            }
            return method.invoke(proxiedClass, args);
        }

        private List<Method> getAnnotatedMethods(Class<? extends Annotation> annotation) {
            annotatedMethodList = new ArrayList<>();
            for (Method method : proxiedClass.getClass().getMethods()) {
                if (!method.isAnnotationPresent(annotation)) {
                    continue;
                }
                annotatedMethodList.add(method);
            }
            return annotatedMethodList;
        }

        private static void doLogging(Method method, Object[] args) {
            System.out.println("executed method: " + method.getName() + ", " + getMethodArgsInfo(args));
        }

        private static String getMethodArgsInfo(Object[] args) {
            var stringJoiner = new StringJoiner(" ");
            stringJoiner.add("args:");
            for (Object arg : args) {
                stringJoiner.add(arg.toString());
            }
            return stringJoiner.toString();
        }
    }
}
