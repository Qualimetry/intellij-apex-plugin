public class AccountService {
    private AccountService AccountService;
    private String serviceName;

    public AccountService(AccountService parent) {
        this.AccountService = parent;
    }

    public void process() {
        AccountService.run();
    }

    private void run() { }
}
