# Excessive Queueable chaining risks stack depth limits

`qa-salesforce-queueable-chain-depth` &middot; Salesforce &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

This rule flags classes that contain more than two System.enqueueJob() calls, indicating excessive Queueable chaining. Each chained Queueable job increases the async stack depth, and Salesforce enforces a maximum chain depth per transaction. Deeply chained jobs are difficult to monitor, debug, and recover from failures. Consider using Batch Apex or a single Queueable that processes work in chunks instead.

## Noncompliant code example

```apex
public class ChainedJob implements Queueable {
    private Integer step;

    public ChainedJob(Integer step) {
        this.step = step;
    }

    public void execute(QueueableContext ctx) {
        if (step == 1) {
            System.enqueueJob(new ChainedJob(2)); // chain
        } else if (step == 2) {
            System.enqueueJob(new ChainedJob(3)); // chain
        } else {
            System.enqueueJob(new ChainedJob(4)); // Noncompliant — excessive chaining
        }
    }
}
```

## Compliant solution

```apex
public class BatchProcessor implements Database.Batchable<SObject> {
    public Database.QueryLocator start(Database.BatchableContext bc) {
        return Database.getQueryLocator([SELECT Id FROM Account LIMIT 200]);
    }

    public void execute(Database.BatchableContext bc, List<SObject> scope) {
        // process chunk
    }

    public void finish(Database.BatchableContext bc) {
        // done
    }
}
```
