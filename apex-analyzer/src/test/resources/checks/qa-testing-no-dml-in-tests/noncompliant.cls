@IsTest
class DmlInTest {
    @IsTest static void testAccounts() {
        Account a = new Account(Name = 'Test');
        insert a;
        Contact c = new Contact(LastName = 'Smith', AccountId = a.Id);
        insert c;
        Account result = [SELECT Id FROM Account WHERE Id = :a.Id];
        Assert.isNotNull(result, 'Account should exist');
    }

    @IsTest static void testOpportunity() {
        Account a = new Account(Name = 'Opp Test');
        insert a;
        Opportunity opp = new Opportunity(Name = 'Deal', AccountId = a.Id,
            StageName = 'Prospecting', CloseDate = Date.today());
        insert opp;
        Assert.areEqual('Prospecting', opp.StageName, 'Stage mismatch');
    }
}
