public class NormalBatch implements Database.Batchable<SObject> {
    public Database.QueryLocator start(Database.BatchableContext bc) {
        return Database.getQueryLocator('SELECT Id, Name FROM Account');
    }

    public void execute(Database.BatchableContext bc, List<Account> scope) {
        for (Account a : scope) {
            a.Description = 'Processed';
        }
        update scope;
    }

    public void finish(Database.BatchableContext bc) { }
}
