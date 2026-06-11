@IsTest
class HasTestMethodTest {
    private static Account createAccount() {
        return new Account(Name = 'Test');
    }

    @IsTest static void testAccountCreation() {
        Account a = createAccount();
        Assert.isNotNull(a, 'Account should be created');
        Assert.areEqual('Test', a.Name, 'Name mismatch');
    }

    @IsTest static void testContactCreation() {
        Contact c = new Contact(LastName = 'Smith');
        Assert.areEqual('Smith', c.LastName, 'Last name mismatch');
    }
}
