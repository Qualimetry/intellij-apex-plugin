@IsTest
class BoolAssertGoodTest {
    @IsTest static void testIsActive() {
        Boolean active = true;
        Assert.isTrue(active, 'should be active');
        Assert.isFalse(!active, 'should not be inactive');
    }

    @IsTest static void testFlag() {
        Boolean flag = false;
        Assert.isFalse(flag, 'flag should be false');
    }
}
