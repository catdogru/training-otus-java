import com.google.gson.Gson;
import json.writer.JsonWriter;
import object.examples.NonPrimitiveFieldsObject;
import object.examples.PrimitiveFieldsObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonWriterTest {
    private PrimitiveFieldsObject testedPrimitiveFieldsObject;
    private NonPrimitiveFieldsObject testedNonPrimitiveFieldsObject;

    @Before
    public void before() {
        testedPrimitiveFieldsObject = new PrimitiveFieldsObject();
        testedNonPrimitiveFieldsObject = new NonPrimitiveFieldsObject();
    }

    @Test
    public void jsonWriterTest() throws IllegalAccessException {
        assertEquals(JsonWriter.buildJsonObject(testedNonPrimitiveFieldsObject).toString(), new Gson().toJson(testedNonPrimitiveFieldsObject));
        assertEquals(JsonWriter.buildJsonObject(testedPrimitiveFieldsObject).toString(), new Gson().toJson(testedPrimitiveFieldsObject));
    }

}