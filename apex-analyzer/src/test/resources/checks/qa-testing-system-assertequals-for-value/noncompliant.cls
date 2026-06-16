@IsTest
class SysAssertEqNonCompliant {
    @IsTest static void testName() {
        String name = 'Hello';
        System.assert(name == 'Hello');
        System.assert(name == 'Hello', 'name mismatch');
    }

    @IsTest static void testCount() {
        Integer count = 5;
        System.assert(count == 5);
    }
}
