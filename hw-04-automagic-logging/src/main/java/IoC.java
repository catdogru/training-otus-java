import annotation.Log;
import calculator.MethodWrapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class IoC {
    public static <I, P extends I> I createProxy(P proxiedObject) {
        InvocationHandler handler = new LoggingInvocationHandler(proxiedObject, Log.class);
        return (I) Proxy.newProxyInstance(IoC.class.getClassLoader(), proxiedObject.getClass().getInterfaces(), handler);
    }

    static class LoggingInvocationHandler implements InvocationHandler {
        private final Object proxiedClass;
        private Set<MethodWrapper> annotatedMethodSet;

        LoggingInvocationHandler(Object proxiedClass, Class<? extends Annotation> annotation) {
            this.proxiedClass = proxiedClass;
            this.annotatedMethodSet = getAnnotatedMethods(annotation);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (annotatedMethodSet.contains(new MethodWrapper(method))) {
                doLogging(method, args);
            }
            return method.invoke(proxiedClass, args);
        }

        private Set<MethodWrapper> getAnnotatedMethods(Class<? extends Annotation> annotation) {
            annotatedMethodSet = new HashSet<>();
            for (Method method : proxiedClass.getClass().getMethods()) {
                if (!method.isAnnotationPresent(annotation)) {
                    continue;
                }
                annotatedMethodSet.add(new MethodWrapper(method));
            }
            return annotatedMethodSet;
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
