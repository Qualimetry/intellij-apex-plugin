# Finally blocks must not throw exceptions

`qa-error-handling-no-throw-in-finally` &middot; Error Handling &middot; Code Smell &middot; severity CRITICAL &middot; enabled in the recommended profile

Throwing an exception from a finally block replaces any exception that was thrown in the try or catch block. The original exception is silently discarded, making it impossible to diagnose the root cause of the failure. Finally blocks should only perform cleanup operations that cannot fail.

## Noncompliant code example

```apex
public class FileProcessor {
    public void processFile(String path) {
        try {
            readFile(path);
        } catch (Exception e) {
            System.debug('Error reading file');
        } finally {
            throw new ApplicationException('cleanup failed'); // Noncompliant - throw in finally
        }
    }

    public void updateRecord(Account acc) {
        try {
            update acc;
        } finally {
            throw new DmlException(); // Noncompliant - throw in finally
        }
    }
    private void readFile(String path) { }
}
```

## Compliant solution

```apex
public class FileProcessor {
    public void processFile(String path) {
        try {
            readFile(path);
        } catch (Exception e) {
            System.debug('Error reading file');
        } finally {
            cleanup();
        }
    }

    public void updateRecord(Account acc) {
        try {
            update acc;
        } finally {
            releaseResources();
        }
    }
    private void readFile(String path) { }
    private void cleanup() { }
    private void releaseResources() { }
}
```
