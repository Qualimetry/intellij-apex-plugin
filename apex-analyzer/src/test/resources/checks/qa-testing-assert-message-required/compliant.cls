@IsTest
class AssertMsgCompliant {
    @IsTest static void testAccount() {
        Account a = new Account(Name = 'Test');
        Assert.isNotNull(a, 'Account should not be null');
        Assert.areEqual('Test', a.Name, 'Name should match');
    }

    @IsTest static void testCalculation() {
        Integer result = 5 * 3;
        Assert.areEqual(15, result, 'Product should be 15');
        Assert.isTrue(result > 0, 'Result should be positive');
    }
}
