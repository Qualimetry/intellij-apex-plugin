@IsTest
class BoolAssertBadTest {
    @IsTest static void testIsActive() {
        Boolean active = true;
        Assert.areEqual(true, active, 'should be active');
        Assert.areEqual(false, !active, 'should not be inactive');
    }

    @IsTest static void testFlag() {
        Boolean flag = false;
        Assert.areEqual(false, flag, 'flag should be false');
    }
}
