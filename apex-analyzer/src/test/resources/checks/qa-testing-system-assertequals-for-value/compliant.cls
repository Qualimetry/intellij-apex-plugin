@IsTest
class SysAssertEqCompliant {
    @IsTest static void testName() {
        String name = 'Hello';
        System.assertEquals('Hello', name);
        System.assertEquals('Hello', name, 'name mismatch');
    }

    @IsTest static void testCount() {
        Integer count = 5;
        System.assertEquals(5, count);
    }
}
