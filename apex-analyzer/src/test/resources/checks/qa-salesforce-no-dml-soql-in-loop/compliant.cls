public class AccountBatchProcessor {

    private List<Account> accountsToProcess;

    public AccountBatchProcessor(List<Account> accounts) {
        this.accountsToProcess = accounts;
    }

    public void processAccounts() {
        update accountsToProcess;
        Integer i = 0;
        do {
            i++;
        } while (i < 10);
    }
}
