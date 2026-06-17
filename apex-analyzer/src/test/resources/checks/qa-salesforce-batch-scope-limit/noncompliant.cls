public class LargeAccountBatch implements Database.Batchable<SObject> {

    public Database.QueryLocator start(Database.BatchableContext bc) {
        return Database.getQueryLocator(
            [SELECT Id, Name FROM Account LIMIT 5000]
        );
    }

    public void execute(Database.BatchableContext bc, List<Account> scope) {
        for (Account a : scope) {
            a.Description = 'Processed';
        }
        update scope;
    }

    public void finish(Database.BatchableContext bc) {
        System.debug(LoggingLevel.INFO, 'Batch complete');
    }
}
