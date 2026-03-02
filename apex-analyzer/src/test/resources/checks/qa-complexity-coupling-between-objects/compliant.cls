public class LowCoupling {
    private AccountService accountSvc;

    public LowCoupling(AccountService svc) {
        this.accountSvc = svc;
    }

    public void processAccount(Id accountId) {
        Account a = accountSvc.getById(accountId);
        a.Description = 'Processed';
        accountSvc.save(a);
    }
}
