@IsTest
public class AssertionRequiredCompliant {
    @IsTest static void testAccountCreation() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        System.assert(acc.Id != null, 'Account should be created');
    }

    @IsTest static void testAccountProcess() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        Test.startTest();
        AccountService.process(acc.Id);
        Test.stopTest();
        Account result = [SELECT Status__c FROM Account WHERE Id = :acc.Id];
        Assert.areEqual('Processed', result.Status__c, 'Should be processed');
    }

    static void run() { }
}
