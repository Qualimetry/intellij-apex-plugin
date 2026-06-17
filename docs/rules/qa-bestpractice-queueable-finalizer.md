# Queueable jobs should attach a Finalizer

`qa-bestpractice-queueable-finalizer` &middot; Best Practice &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

This rule flags Queueable classes that do not attach a Finalizer via System.attachFinalizer(). Finalizers execute after a Queueable job completes, whether it succeeds or fails, providing a reliable mechanism for cleanup, logging, and retry logic. Without a finalizer, failed jobs may go unnoticed and leave the system in an inconsistent state.

## Noncompliant code example

```apex
public class DataSyncJob implements Queueable { // Noncompliant
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
```

## Compliant solution

```apex
public class DataSyncJob implements Queueable {
    private List<Account> accounts;

    public DataSyncJob(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void execute(QueueableContext ctx) {
        System.attachFinalizer(new DataSyncFinalizer());
        for (Account a : accounts) {
            a.Description = 'Synced';
        }
        update accounts;
    }
}
```
