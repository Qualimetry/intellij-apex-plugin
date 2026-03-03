public class NotTestClass {
    public void doWork() {
        Account a = new Account(Name = 'Real');
    }

    @testMethod static void testDoWork() {
        NotTestClass svc = new NotTestClass();
        svc.doWork();
    }

    @testMethod static void testAnother() {
        Assert.isTrue(true, 'Always passes');
    }
}
