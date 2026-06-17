public class AccountService {
    public void createAccount(String name) {
        Account acc = new Account(Name = name);
        insert acc;
    }

    public List<Account> findByName(String name) {
        return new List<Account>();
    }

    private void validate() { }
}
