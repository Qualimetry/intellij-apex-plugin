# Review Database.AllowsCallouts in batch classes

`qa-salesforce-review-batch-callouts` &middot; Salesforce &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Batch Apex classes that implement Database.AllowsCallouts can make HTTP callouts from within batch execution, but this introduces risks: callout failures can leave the batch in an inconsistent state, each callout consumes governor limits, and retry logic is complex. Review every batch callout for proper error handling, timeout configuration, and idempotency.

## Noncompliant code example

```apex
global class SyncBatch implements Database.Batchable<SObject>, Database.AllowsCallouts {
    global Database.QueryLocator start(Database.BatchableContext bc) {
        return Database.getQueryLocator('SELECT Id, Name FROM Account');
    }

    global void execute(Database.BatchableContext bc, List<Account> scope) {
        for (Account acc : scope) {
            HttpRequest req = new HttpRequest();               // Noncompliant — review
            req.setEndpoint('callout:ExternalCRM/sync');
            req.setMethod('POST');
            new Http().send(req);
        }
    }

    global void finish(Database.BatchableContext bc) { }
}
```

## Compliant solution

```apex
global class SyncBatch implements Database.Batchable<SObject>, Database.AllowsCallouts {
    global Database.QueryLocator start(Database.BatchableContext bc) {
        return Database.getQueryLocator('SELECT Id, Name FROM Account');
    }

    global void execute(Database.BatchableContext bc, List<Account> scope) {
        // Reviewed: error handling, timeout, and idempotency verified
        ExternalCrmService.syncAccounts(scope);
    }

    global void finish(Database.BatchableContext bc) { }
}
```
