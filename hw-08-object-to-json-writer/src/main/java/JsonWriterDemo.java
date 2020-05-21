import com.google.gson.Gson;
import object.examples.NonPrimitiveFieldsObject;
import object.examples.PrimitiveFieldsObject;

import static json.writer.JsonWriter.buildJsonObject;

public class JsonWriterDemo {
    public static void main(String... args) throws IllegalAccessException {
        var nonPrimitiveFieldsObject = new NonPrimitiveFieldsObject();
        var primitiveFieldsObject = new PrimitiveFieldsObject();

        System.out.println("GSON Writer:\n" + new Gson().toJson(nonPrimitiveFieldsObject));
        System.out.println("JsonWriter:\n" + buildJsonObject(nonPrimitiveFieldsObject));
        System.out.println("\n");
        System.out.println("GSON Writer:\n" + new Gson().toJson(primitiveFieldsObject));
        System.out.println("JsonWriter:\n" + buildJsonObject(primitiveFieldsObject));
    }


}