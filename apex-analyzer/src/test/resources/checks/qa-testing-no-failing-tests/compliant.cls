@IsTest
class PassingTest {
    @IsTest static void testAddition() {
        Integer result = 2 + 2;
        Assert.areEqual(4, result, 'Sum should be 4');
    }

    @IsTest static void testNotNull() {
        Account a = new Account(Name = 'Test');
        Assert.isNotNull(a, 'Account should exist');
        Assert.areEqual('Test', a.Name, 'Name should match');
    }
}
