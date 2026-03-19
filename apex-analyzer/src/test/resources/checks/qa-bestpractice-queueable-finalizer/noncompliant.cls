public class DataSyncJob implements Queueable {
    private List<Account> accounts;

    public DataSyncJob(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void execute(QueueableContext ctx) {
        for (Account a : accounts) {
            a.Description = 'Synced';
        }
        update accounts;
    }
}
