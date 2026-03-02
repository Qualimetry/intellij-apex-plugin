public class AccountProcessorCompliant {
    private List<Account> accounts;

    public AccountProcessorCompliant(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void process() {
        System.debug(LoggingLevel.INFO, 'Starting account processing');
        for (Account a : accounts) {
            a.Description = 'Processed';
            System.debug(LoggingLevel.FINE, 'Processing: ' + a.Name);
        }
        update accounts;
        System.debug(LoggingLevel.INFO, 'Done');
    }
}
