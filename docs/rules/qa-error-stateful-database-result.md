# Stateful Batch should not store Database.SaveResult

`qa-error-stateful-database-result` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

This rule flags Database.Stateful batch classes that store Database.SaveResult objects as instance fields. When a batch class implements Database.Stateful, instance variables are serialized between execute() invocations. Database.SaveResult is not designed for serialization and will cause runtime errors. Use simple counters or custom summary objects to track outcomes across batches.

## Noncompliant code example

```apex
public class AccountCleanupBatch implements Database.Batchable<SObject>, Database.Stateful {
    List<Database.SaveResult> allResults = new List<Database.SaveResult>(); // Noncompliant

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
```

## Compliant solution

```apex
public class AccountCleanupBatch implements Database.Batchable<SObject>, Database.Stateful {
    Integer successCount = 0;
    Integer failureCount = 0;

    public Database.QueryLocator start(Database.BatchableContext bc) {
        return Database.getQueryLocator([SELECT Id FROM Account WHERE IsActive__c = false]);
    }

    public void execute(Database.BatchableContext bc, List<Account> scope) {
        List<Database.SaveResult> results = Database.delete(scope, false);
        for (Database.SaveResult sr : results) {
            if (sr.isSuccess()) { successCount++; } else { failureCount++; }
        }
    }

    public void finish(Database.BatchableContext bc) {
        System.debug(LoggingLevel.INFO, 'Success: ' + successCount + ', Failures: ' + failureCount);
    }
}
```
