# Batch Apex scope should not exceed 2000 records

`qa-salesforce-batch-scope-limit` &middot; Salesforce &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

This rule flags Batch Apex queries that specify a LIMIT greater than 2,000 records within a Database.getQueryLocator() call. Although Batch Apex can process up to 50 million records overall, individual batch scope sizes above 2,000 can cause heap and CPU limit exceptions. Keep batch scope within reasonable bounds to ensure reliable processing.

## Noncompliant code example

```apex
public class LargeAccountBatch implements Database.Batchable<SObject> {
    public Database.QueryLocator start(Database.BatchableContext bc) {
        return Database.getQueryLocator(
            [SELECT Id, Name FROM Account LIMIT 5000] // Noncompliant — exceeds 2000
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
```

## Compliant solution

```apex
public class LargeAccountBatch implements Database.Batchable<SObject> {
    public Database.QueryLocator start(Database.BatchableContext bc) {
        return Database.getQueryLocator(
            [SELECT Id, Name FROM Account LIMIT 200]
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
```
