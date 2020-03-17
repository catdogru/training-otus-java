import annotation.After;
import annotation.Before;
import annotation.Test;
import helper.AnnotatedMethodFilter;
import test.TestClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestLauncher {
    private List<Class<? extends Annotation>> annotationList = List.of(Before.class, Test.class, After.class);
    private Map<Class<? extends Annotation>, Set<Method>> filteredMethodMap;
    private TestStats stats = new TestStats();

    public void runTests(TestClass testClass) throws ClassNotFoundException {
        Class clazz = Class.forName(testClass.getClass().getName());
        filteredMethodMap = AnnotatedMethodFilter.getFilteredMethodMap(clazz, annotationList);
        runTestMethods(filteredMethodMap.get(Test.class));
        System.out.println(stats.toString());
    }

    private void runTestMethods(Set<Method> methodSet) {
        for (Method method : methodSet) {
            try {
                safeInvokeMethods(filteredMethodMap.get(Before.class));
                method.invoke(method.getParameters());
                stats.handleTestResult("SUCCESS");
            } catch (Exception e) {
                stats.handleTestResult("FAIL");
            } finally {
                safeInvokeMethods(filteredMethodMap.get(After.class));
            }
        }
    }

    private void safeInvokeMethods(Set<Method> methodSet) {
        for (Method method : methodSet) {
            try {
                method.invoke(method.getParameters());
            } catch (Exception ignored) {
            }
        }
    }

    private static class TestStats {
        private int failedCount;
        private int successCount;

        void handleTestResult(String testResult) {
            switch (testResult) {
                case "SUCCESS" : successCount++; break;
                case "FAIL": failedCount++;
            }
        }

        @Override
        public String toString() {
            return "\n" +"total test count: " + (successCount + failedCount) + "\n" +
                    "failedCount: " + failedCount + "\n" +
                    "successCount: " + successCount;
        }
    }
}
