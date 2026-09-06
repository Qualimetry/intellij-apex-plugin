public class AccountProcessor {
    private List<Account> accounts;

    public AccountProcessor(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void process() {
        System.debug('Starting account processing');
        for (Account a : accounts) {
            a.Description = 'Processed';
            System.debug('Processing: ' + a.Name);
        }
        update accounts;
        System.debug('Finished processing ' + accounts.size() + ' accounts');
    }
}
