public class AccountService {
    private List<Account> accounts;

    public AccountService() {
        this.accounts = new List<Account>();
    }

    public List<Account> getActiveAccounts() {
        return [SELECT Id, Name FROM Account WHERE IsActive__c = true];
    }

    public void deactivateAccount(Id accountId) {
        Account a = [SELECT Id FROM Account WHERE Id = :accountId];
        a.IsActive__c = false;
        update a;
    }
}
