import test.TestClass;

public class Main {
    public static void main(String... args) {
        try {
            new TestLauncher().runTests(new TestClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
