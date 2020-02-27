package helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AnnotatedMethodFilter {
    public static Map<Class<? extends Annotation>, Set<Method>> getFilteredMethodMap(Class clazz, List<Class<? extends Annotation>> annotationList) {
        return filterMethodsByAnnotation(initializeAnnotatedMethodMap(annotationList), clazz.getMethods());
    }

    private static Map<Class<? extends Annotation>, Set<Method>> filterMethodsByAnnotation(Map<Class<? extends Annotation>, Set<Method>> annotatedMethodMap, Method... methods) {
        for (Method method : methods) {
            for (Class<? extends Annotation> annotation : annotatedMethodMap.keySet()) {
                if (method.isAnnotationPresent(annotation)) {
                    annotatedMethodMap.get(annotation).add(method);
                }
            }
        }
        return annotatedMethodMap;
    }

    private static Map<Class<? extends Annotation>, Set<Method>> initializeAnnotatedMethodMap(List<Class<? extends Annotation>> annotationList) {
        return annotationList.stream()
                .collect(Collectors.toMap(annotation -> annotation, annotation -> new HashSet<>()));
    }
}
