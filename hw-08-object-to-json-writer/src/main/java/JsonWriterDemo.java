import com.google.gson.Gson;
import object.examples.NonPrimitiveFieldsObject;
import object.examples.NonSerializableFieldsObject;
import object.examples.PrimitiveFieldsObject;

import static json.writer.JsonWriter.toJsonString;

public class JsonWriterDemo {
    public static void main(String... args) throws IllegalAccessException {
        var nonPrimitiveFieldsObject = new NonPrimitiveFieldsObject();
        var primitiveFieldsObject = new PrimitiveFieldsObject();
        var nonSerializableFieldsObject = new NonSerializableFieldsObject();

        System.out.println("GSON Writer:\n" + new Gson().toJson(nonPrimitiveFieldsObject));
        System.out.println("JsonWriter:\n" + toJsonString(nonPrimitiveFieldsObject));
        System.out.println("\n");
        System.out.println("GSON Writer:\n" + new Gson().toJson(primitiveFieldsObject));
        System.out.println("JsonWriter:\n" + toJsonString(primitiveFieldsObject));
        System.out.println("\n");
        System.out.println("GSON Writer:\n" + new Gson().toJson(nonSerializableFieldsObject));
        System.out.println("JsonWriter:\n" + toJsonString(nonSerializableFieldsObject));
    }


}