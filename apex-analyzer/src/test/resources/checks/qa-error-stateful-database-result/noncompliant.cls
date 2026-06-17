public class AccountCleanupBatch implements Database.Batchable<SObject>, Database.Stateful {
    List<Database.SaveResult> allResults = new List<Database.SaveResult>();

    public Database.QueryLocator start(Database.BatchableContext bc) {
        return Database.getQueryLocator([SELECT Id FROM Account WHERE IsActive__c = false]);
    }

    public void execute(Database.BatchableContext bc, List<Account> scope) {
        List<Database.SaveResult> results = Database.delete(scope, false);
        allResults.addAll(results);
    }

    public void finish(Database.BatchableContext bc) {
        System.debug(LoggingLevel.INFO, 'Results: ' + allResults.size());
    }
}
