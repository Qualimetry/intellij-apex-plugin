public class AccountBatchProcessor {

    private List<Account> accountsToProcess;

    public AccountBatchProcessor(List<Account> accounts) {
        this.accountsToProcess = accounts;
    }

    public void processAccounts() {
        for (Account a : accountsToProcess) {
            update a;
        }
        List<Contact> contacts = new List<Contact>();
        for (Contact c : contacts) {
            List<Contact> results = [SELECT Id FROM Contact];
        }
    }
}
