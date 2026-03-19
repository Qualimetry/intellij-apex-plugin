public class MyController {
    @auraenabled
    public static String getName() {
        return 'Hello';
    }

    @istest
    static void testGetName() {
        System.assertEquals('Hello', MyController.getName());
    }

    @future
    public static void doAsync() {
        System.debug(LoggingLevel.INFO, 'Async work');
    }
}
