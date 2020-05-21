package object.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NonPrimitiveFieldsObject {
    private String nullString = null;
    private String stringField = "some value";
    private int[] arr = {1, 52, 467};
    private Set setString = Set.of("i am string", "i am string too");
    private Set setInt = Set.of(12312, 234234);
    private Map<String, String> stringKeyMap = Map.of("ololo", "trololo");
    private Map<PrimitiveFieldsObject, String> objectKeyMap = Map.of(new PrimitiveFieldsObject(), "i am value");
    private List<PrimitiveFieldsObject> objectList = List.of(new PrimitiveFieldsObject(), new PrimitiveFieldsObject());
}