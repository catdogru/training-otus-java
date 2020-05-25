import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json.writer.JsonWriter;
import object.examples.NonPrimitiveFieldsObject;
import object.examples.NonSerializableFieldsObject;
import object.examples.PrimitiveFieldsObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonWriterTest {
    private Gson gson;
    private PrimitiveFieldsObject testedPrimitiveFieldsObject;
    private NonPrimitiveFieldsObject testedNonPrimitiveFieldsObject;
    private NonSerializableFieldsObject testedNonSerializableFieldsObject;

    @Before
    public void before() {
        gson = new GsonBuilder().serializeNulls().create();
        testedPrimitiveFieldsObject = new PrimitiveFieldsObject();
        testedNonPrimitiveFieldsObject = new NonPrimitiveFieldsObject();
        testedNonSerializableFieldsObject = new NonSerializableFieldsObject();
    }

    @Test
    public void objectToJsonTest() throws IllegalAccessException {
        assertEquals(JsonWriter.toJsonString(testedNonPrimitiveFieldsObject), gson.toJson(testedNonPrimitiveFieldsObject));
        assertEquals(JsonWriter.toJsonString(testedPrimitiveFieldsObject), gson.toJson(testedPrimitiveFieldsObject));
    }

    @Test
    public void primitiveToJsonTest() throws IllegalAccessException {
        assertEquals(gson.toJson(null), JsonWriter.toJsonString(null));
        assertEquals(gson.toJson(true), JsonWriter.toJsonString(true));
        assertEquals(gson.toJson(false), JsonWriter.toJsonString(false));
        assertEquals(gson.toJson((byte)1), JsonWriter.toJsonString((byte)1));
        assertEquals(gson.toJson((short)2f), JsonWriter.toJsonString((short)2f));
        assertEquals(gson.toJson(3), JsonWriter.toJsonString(3));
        assertEquals(gson.toJson(4L), JsonWriter.toJsonString(4L));
        assertEquals(gson.toJson(5f), JsonWriter.toJsonString(5f));
        assertEquals(gson.toJson(6d), JsonWriter.toJsonString(6d));
        assertEquals(gson.toJson("aaa"), JsonWriter.toJsonString("aaa"));
        assertEquals(gson.toJson('b'), JsonWriter.toJsonString('b'));
        assertEquals(gson.toJson(new int[] {1, 2, 3}), JsonWriter.toJsonString(new int[] {1, 2, 3}));
        assertEquals(gson.toJson(List.of(4, 5 ,6)), JsonWriter.toJsonString(List.of(4, 5 ,6)));
        assertEquals(gson.toJson(Collections.singletonList(7)), JsonWriter.toJsonString(Collections.singletonList(7)));
    }

    @Test
    public void nonSerializableFieldToJsonTest() throws IllegalAccessException {
        assertEquals(gson.toJson(testedNonSerializableFieldsObject), JsonWriter.toJsonString(testedNonSerializableFieldsObject));
    }
}