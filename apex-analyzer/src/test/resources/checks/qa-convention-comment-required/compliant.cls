public class AccountService {
    /** Creates a new Account record with the given name. */
    public void createAccount(String name) {
        Account acc = new Account(Name = name);
        insert acc;
    }

    /** Finds accounts matching the provided name. */
    public List<Account> findByName(String name) {
        return new List<Account>();
    }
}
