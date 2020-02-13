package calculator;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class MethodWrapper {
    private String methodName;
    private Class<?>[] parameterTypes;

    public MethodWrapper(Method method) {
        this.methodName = method.getName();
        this.parameterTypes = method.getParameterTypes();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodWrapper that = (MethodWrapper) o;
        return Objects.equals(methodName, that.methodName) &&
                Arrays.equals(parameterTypes, that.parameterTypes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(methodName);
        result = 31 * result + Arrays.hashCode(parameterTypes);
        return result;
    }
}
