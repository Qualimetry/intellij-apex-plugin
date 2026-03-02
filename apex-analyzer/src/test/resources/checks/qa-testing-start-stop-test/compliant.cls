@IsTest
public class StartStopTestCompliant {
    @IsTest static void testAccountProcess() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        Test.startTest();
        AccountService.process(acc.Id);
        Test.stopTest();
        Account result = [SELECT Status__c FROM Account WHERE Id = :acc.Id];
        Assert.areEqual('Processed', result.Status__c, 'Should be processed');
    }

    @IsTest static void testBatch() {
        List<Account> accounts = new List<Account>();
        for (Integer i = 0; i < 10; i++) {
            accounts.add(new Account(Name = 'Test ' + i));
        }
        insert accounts;
        Test.startTest();
        Database.executeBatch(new CleanupBatch());
        Test.stopTest();
        Assert.areEqual(10, [SELECT COUNT() FROM Account], 'All accounts remain');
    }

    static void run() { }
}
