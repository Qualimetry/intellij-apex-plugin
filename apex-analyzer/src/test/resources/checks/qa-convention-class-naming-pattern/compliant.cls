public class AccountService {
    private List<Account> accounts;

    public AccountService() {
        this.accounts = new List<Account>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account a) {
        accounts.add(a);
    }
}
