/**
 * Provides services for managing Account records including
 * retrieval and lifecycle operations.
 */
public class AccountServiceDocumented {
    private List<Account> accounts;

    public AccountServiceDocumented() {
        this.accounts = new List<Account>();
    }

    public List<Account> getActiveAccounts() {
        return [SELECT Id, Name FROM Account WHERE IsActive__c = true];
    }
}
