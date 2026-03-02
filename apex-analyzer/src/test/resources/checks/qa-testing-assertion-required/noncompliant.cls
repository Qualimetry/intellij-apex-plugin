@IsTest
public class AssertionRequiredNonCompliant {
    @IsTest static void testWithAssert() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        Assert.isTrue(true, 'ok');
    }

    @IsTest static void testWithoutAssert() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        AccountService.process(acc.Id);
    }

    @IsTest static void testDeleteWithoutAssert() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        delete acc;
    }

    static void run() { }
}
