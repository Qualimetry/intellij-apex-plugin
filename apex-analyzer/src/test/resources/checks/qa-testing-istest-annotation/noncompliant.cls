public class MyTestClass {

    private Integer count;

    public void setup() {
        count = 0;
    }

    testMethod static void testCounter() {
        MyTestClass obj = new MyTestClass();
        obj.setup();
    }
}
