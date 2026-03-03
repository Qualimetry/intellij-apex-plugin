public class AccountManager {
    private List<Account> accountsToCreate;

    public AccountManager(List<Account> accounts) {
        this.accountsToCreate = accounts;
    }

    public void createAccounts() {
        insert accountsToCreate;
    }

    public void removeAccount(Id accountId) {
        Account a = [SELECT Id FROM Account WHERE Id = :accountId];
        delete a;
    }
}
