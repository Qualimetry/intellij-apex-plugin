public class my_account_service {
    private List<Account> accounts;

    public my_account_service() {
        this.accounts = new List<Account>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account a) {
        accounts.add(a);
    }
}
