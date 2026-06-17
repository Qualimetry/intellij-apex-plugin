# Batch Apex should not be invoked from triggers

`qa-salesforce-no-batch-from-trigger` &middot; Salesforce &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Invoking batch Apex (Database.executeBatch()) from a trigger is dangerous because triggers can fire multiple times in a single transaction (e.g., from workflow field updates, process builders, or recursive trigger execution). Each invocation queues a batch job, quickly exhausting the 5-job concurrent batch limit and causing runtime errors. Use Queueable Apex or a trigger handler pattern that defers batch execution.

## Noncompliant code example

```apex
trigger AccountTrigger on Account (after update) {
    Database.executeBatch(new AccountCleanupBatch());  // Noncompliant — batch from trigger
}
```

## Compliant solution

```apex
trigger AccountTrigger on Account (after update) {
    System.enqueueJob(new AccountCleanupQueueable(Trigger.newMap.keySet()));
}
```
