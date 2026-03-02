public class AccountManagerCompliant {
    private List<Account> accountsToCreate;

    public AccountManagerCompliant(List<Account> accounts) {
        this.accountsToCreate = accounts;
    }

    public void createAccounts() {
        if (Schema.sObjectType.Account.isCreateable()) {
            insert accountsToCreate;
        }
    }

    public void removeAccount(Id accountId) {
        if (Schema.sObjectType.Account.isDeletable()) {
            Account a = [SELECT Id FROM Account WHERE Id = :accountId];
            delete a;
        }
    }
}
