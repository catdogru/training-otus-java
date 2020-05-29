package json.writer;

import java.util.Collection;
import java.util.Map;

public enum FieldTypeEnum {
    BOOLEAN,
    BYTE,
    SHORT,
    INTEGER,
    LONG,
    FLOAT,
    DOUBLE,
    CHAR,
    STRING,
    ARRAY,
    COLLECTION,
    MAP,
    OBJECT;

    public static FieldTypeEnum parseFieldType(Class<?> fieldType) {
        if (boolean.class.equals(fieldType) || Boolean.class.equals(fieldType)) return BOOLEAN;
        if (byte.class.equals(fieldType) || Byte.class.equals(fieldType)) return BYTE;
        if (short.class.equals(fieldType) || Short.class.equals(fieldType)) return SHORT;
        if (int.class.equals(fieldType) || Integer.class.equals(fieldType)) return INTEGER;
        if (long.class.equals(fieldType) || Long.class.equals(fieldType)) return LONG;
        if (float.class.equals(fieldType) || Float.class.equals(fieldType)) return FLOAT;
        if (double.class.equals(fieldType) || Double.class.equals(fieldType)) return DOUBLE;
        if (char.class.equals(fieldType) || Character.class.equals(fieldType)) return CHAR;
        if (String.class.equals(fieldType)) return STRING;
        if (fieldType.isArray()) return ARRAY;
        if (Collection.class.isAssignableFrom(fieldType)) return COLLECTION;
        if (Map.class.isAssignableFrom(fieldType)) return MAP;
        return OBJECT;
    }
}
