@IsTest
class AssertClassTest {
    @IsTest static void testAccount() {
        Account a = new Account(Name = 'Test');
        Assert.isNotNull(a, 'Account should not be null');
        Assert.areEqual('Test', a.Name, 'Name mismatch');
        Assert.isNotNull(a.Name, 'Name should not be null');
    }

    @IsTest static void testCalculation() {
        Integer result = 2 + 3;
        Assert.isTrue(result > 0, 'Result should be positive');
        Assert.areEqual(5, result, 'Sum should be 5');
    }
}
