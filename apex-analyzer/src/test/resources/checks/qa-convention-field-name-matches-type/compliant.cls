public class AccountService {
    private AccountService delegate;
    private String serviceName;

    public AccountService(AccountService parent) {
        this.delegate = parent;
    }

    public void process() {
        delegate.run();
    }

    private void run() { }
}
