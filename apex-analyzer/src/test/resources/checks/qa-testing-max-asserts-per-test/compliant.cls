@IsTest
class FocusedAssertsTest {
    @IsTest static void testAccountName() {
        Account a = new Account(Name = 'Test');
        Assert.isNotNull(a, 'Account not null');
        Assert.areEqual('Test', a.Name, 'Name matches');
    }

    @IsTest static void testAccountIndustry() {
        Account a = new Account(Industry = 'Tech');
        Assert.areEqual('Tech', a.Industry, 'Industry matches');
        Assert.isNotNull(a.Industry, 'Industry set');
    }
}
