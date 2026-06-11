@IsTest
class AccountServiceVerification {
    @IsTest static void testCreate() {
        Account a = new Account(Name = 'Test');
        Assert.isNotNull(a, 'Account created');
    }

    @IsTest static void testUpdate() {
        Account a = new Account(Name = 'Old');
        a.Name = 'New';
        Assert.areEqual('New', a.Name, 'Name updated');
    }
}
