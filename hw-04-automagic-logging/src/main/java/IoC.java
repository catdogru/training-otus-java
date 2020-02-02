import annotation.Log;
import calculator.CalculatorImpl;
import calculator.CalculatorInterface;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class IoC {
    public static CalculatorInterface createCalculator() {
        InvocationHandler handler = new CalculatorInvocationHandler(new CalculatorImpl());
        return (CalculatorInterface) Proxy.newProxyInstance(IoC.class.getClassLoader(), new Class<?>[]{CalculatorInterface.class}, handler);
    }

    static class CalculatorInvocationHandler implements InvocationHandler {
        private final CalculatorInterface calculator;

        CalculatorInvocationHandler(CalculatorInterface calculator) {
            this.calculator = calculator;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (Method annotatedMethod : getAnnotatedMethods(Log.class)) {
                if (!annotatedMethod.getName().equals(method.getName())) {
                    continue;
                }
                doLogging(method, args);
                break;
            }
            return method.invoke(calculator, args);
        }

        private List<Method> getAnnotatedMethods(Class<? extends Annotation> annotation) {
            List<Method> annotatedMethodList = new ArrayList<>();
            for (Method method : calculator.getClass().getMethods()) {
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
