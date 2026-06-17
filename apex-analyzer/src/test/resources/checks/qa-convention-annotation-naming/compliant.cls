public class MyControllerCompliant {
    @AuraEnabled
    public static String getName() {
        return 'Hello';
    }

    @IsTest
    static void testGetName() {
        System.assertEquals('Hello', MyControllerCompliant.getName());
    }

    @Future
    public static void doAsync() {
        System.debug(LoggingLevel.INFO, 'Async work');
    }
}
