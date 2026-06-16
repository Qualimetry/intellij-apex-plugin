public class AccountService {

    private List<Account> accounts;

    public AccountService() {
        this.accounts = new List<Account>();
    }

    public List<Account> getAccounts() {
        return [SELECT Id, Name FROM Account];
    }

    public void addAccount(Account acc) {
        accounts.add(acc);
    }
}
