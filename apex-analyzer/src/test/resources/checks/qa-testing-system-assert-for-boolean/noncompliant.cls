@IsTest
class SysAssertBoolNonCompliant {
    @IsTest static void testFlag() {
        Boolean flag = true;
        System.assertEquals(true, flag);
        System.assertEquals(true, flag, 'should be true');
    }

    @IsTest static void testCondition() {
        Boolean result = 5 > 3;
        System.assertEquals(true, result);
    }
}
