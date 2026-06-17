public class DataSyncJobWithFinalizer implements Queueable {
    private List<Account> accounts;

    public DataSyncJobWithFinalizer(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void execute(QueueableContext ctx) {
        System.attachFinalizer(new DataSyncFinalizer());
        for (Account a : accounts) {
            a.Description = 'Synced';
        }
        update accounts;
    }
}
