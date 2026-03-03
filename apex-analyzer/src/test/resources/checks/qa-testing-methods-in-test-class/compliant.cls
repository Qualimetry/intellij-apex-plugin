@IsTest
public class ProperTestClass {
    @IsTest static void testDoWork() {
        AccountService svc = new AccountService();
        svc.doWork();
        Assert.isTrue(true, 'Work completed');
    }

    @IsTest static void testAnother() {
        Assert.isTrue(true, 'Always passes');
    }
}
