@IsTest
class FailTest {
    @IsTest static void testAlwaysFails() {
        Assert.isTrue(false);
    }

    @IsTest static void testBrokenLogic() {
        Integer result = 2 + 2;
        Assert.areEqual(5, result, 'Math is broken');
    }

    @IsTest static void testNullPointer() {
        Account a;
        Assert.areEqual('Test', a.Name, 'Should not reach here');
    }
}
