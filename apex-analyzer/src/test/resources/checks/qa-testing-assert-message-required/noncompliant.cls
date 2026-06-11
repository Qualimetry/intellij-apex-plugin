@IsTest
class AssertMsgNonCompliant {
    @IsTest static void testAccount() {
        Account a = new Account(Name = 'Test');
        Assert.isNotNull(a);
        Assert.areEqual('Test', a.Name);
    }

    @IsTest static void testCalculation() {
        Integer result = 5 * 3;
        Assert.areEqual(15, result);
        Assert.isTrue(result > 0);
    }
}
