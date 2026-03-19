@IsTest
public class StartStopTestNonCompliant {
    @IsTest static void testWithStartStop() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        Test.startTest();
        AccountService.process(acc.Id);
        Test.stopTest();
        Assert.isNotNull(acc.Id, 'Account created');
    }

    @IsTest static void testWithoutStartStop() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        AccountService.process(acc.Id);
        Assert.isNotNull(acc.Id, 'Account created');
    }

    @IsTest static void testBatchWithoutBracket() {
        List<Account> accounts = new List<Account>();
        for (Integer i = 0; i < 10; i++) {
            accounts.add(new Account(Name = 'Test ' + i));
        }
        insert accounts;
        Database.executeBatch(new CleanupBatch());
    }

    static void run() { }
}
