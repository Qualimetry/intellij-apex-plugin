public class AccountCleanupBatchCompliant implements Database.Batchable<SObject>, Database.Stateful {
    Integer successCount = 0;
    Integer failureCount = 0;

    public Database.QueryLocator start(Database.BatchableContext bc) {
        return Database.getQueryLocator([SELECT Id FROM Account WHERE IsActive__c = false]);
    }

    public void execute(Database.BatchableContext bc, List<Account> scope) {
        delete scope;
        successCount += scope.size();
    }

    public void finish(Database.BatchableContext bc) {
        System.debug(LoggingLevel.INFO, 'Success: ' + successCount);
    }
}
