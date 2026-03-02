public class NoReflection {
    public void run() {
        MyHandler handler = new MyHandler();
        handler.execute();
    }

    @AuraEnabled
    public static Object createInstance() {
        return new DefaultHandler();
    }
}
