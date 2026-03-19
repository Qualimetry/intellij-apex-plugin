@IsTest(isParallel=true)
class ParallelTest {
    @IsTest static void testOne() {
        Account a = new Account(Name = 'Test');
        Assert.isNotNull(a, 'Account created');
    }

    @IsTest static void testTwo() {
        Contact c = new Contact(LastName = 'Smith');
        Assert.areEqual('Smith', c.LastName, 'Last name matches');
    }
}
