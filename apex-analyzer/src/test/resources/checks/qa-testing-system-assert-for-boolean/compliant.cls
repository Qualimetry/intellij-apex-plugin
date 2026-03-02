@IsTest
class SysAssertBoolCompliant {
    @IsTest static void testFlag() {
        Boolean flag = true;
        System.assert(flag);
        System.assert(flag, 'should be true');
    }

    @IsTest static void testCondition() {
        Boolean result = 5 > 3;
        System.assert(result);
    }
}
