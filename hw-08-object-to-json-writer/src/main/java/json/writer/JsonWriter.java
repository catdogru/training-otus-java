package json.writer;

import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public abstract class JsonWriter {
    public static JsonObject buildJsonObject(Object object) throws IllegalAccessException {
        JsonObjectBuilder jsonObject = Json.createObjectBuilder();
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            String fieldName = field.getName();
            Object fieldValue = field.get(object);

            if (fieldValue == null) continue;

            jsonObject.add(fieldName, getJsonValue(fieldValue));
        }
        return jsonObject.build();
    }

    private static JsonValue getJsonValue(Object fieldValue) throws IllegalAccessException {
        Class<?> fieldType = fieldValue.getClass();
        switch (FieldTypeEnum.parseFieldType(fieldType)) {
            case BOOLEAN:
                return (boolean) fieldValue ? JsonValue.TRUE : JsonValue.FALSE;
            case BYTE:
                return Json.createValue((byte) fieldValue);
            case SHORT:
                return Json.createValue((short) fieldValue);
            case INTEGER:
                return Json.createValue((int) fieldValue);
            case LONG:
                return Json.createValue((long) fieldValue);
            case FLOAT:
                return Json.createValue((float) fieldValue);
            case DOUBLE:
                return Json.createValue((double) fieldValue);
            case CHAR:
                return Json.createValue(((Character) fieldValue).toString());
            case STRING:
                return Json.createValue((String) fieldValue);
            case ARRAY:
                return buildJsonArray(fieldValue);
            case COLLECTION:
                return buildJsonArray(((Collection) fieldValue).toArray());
            case MAP:
                return buildJsonMap((Map<Object, Object>) fieldValue);
            case OBJECT:
                return buildJsonObject(fieldValue);
            default:
                return JsonValue.EMPTY_JSON_OBJECT;
        }
    }

    private static JsonArray buildJsonArray(Object object) throws IllegalAccessException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        int arraySize = Array.getLength(object);
        for (int i = 0; i < arraySize; i++) {
            Object arrayElementValue = Array.get(object, i);
            arrayBuilder.add(getJsonValue(arrayElementValue));
        }
        return arrayBuilder.build();
    }

    private static JsonObject buildJsonMap(Map<Object, Object> map) throws IllegalAccessException {
        JsonObjectBuilder mapBuilder = Json.createObjectBuilder();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            mapBuilder.add(entry.getKey().toString(), getJsonValue(entry.getValue()));
        }
        return mapBuilder.build();
    }
}
