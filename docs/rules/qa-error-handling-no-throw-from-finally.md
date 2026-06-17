# Finally blocks must not throw exceptions directly

`qa-error-handling-no-throw-from-finally` &middot; Error Handling &middot; Code Smell &middot; severity CRITICAL &middot; enabled in the recommended profile

Throwing an exception directly from a finally block discards any exception that was propagating from the try or catch block. The original failure is silently lost, making the root cause of production issues impossible to determine. Finally blocks should only contain cleanup code that cannot throw.

## Noncompliant code example

```apex
public class TransactionManager {
    public void execute() {
        try {
            performTransaction();
        } catch (DmlException e) {
            System.debug('Transaction failed: ' + e.getMessage());
        } finally {
            throw new ApplicationException('Cleanup error'); // Noncompliant
        }
    }

    public void process() {
        try {
            loadData();
        } finally {
            throw new RuntimeException('Finalize error'); // Noncompliant
        }
    }
    private void performTransaction() { }
    private void loadData() { }
}
```

## Compliant solution

```apex
public class TransactionManager {
    public void execute() {
        try {
            performTransaction();
        } catch (DmlException e) {
            System.debug('Transaction failed: ' + e.getMessage());
        } finally {
            safeCleanup();
        }
    }

    public void process() {
        try {
            loadData();
        } finally {
            safeCleanup();
        }
    }
    private void performTransaction() { }
    private void loadData() { }
    private void safeCleanup() { }
}
```
