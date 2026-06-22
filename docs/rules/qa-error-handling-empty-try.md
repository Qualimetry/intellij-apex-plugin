# Try blocks must contain at least one statement

`qa-error-handling-empty-try` &middot; Error Handling &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

An empty try block indicates incomplete error-handling implementation. The try block was created to protect some operation, but the operation was never added. Remove the empty try-catch structure or add the intended logic.

## Noncompliant code example

```apex
public class SafeExecutor {
    public void execute() {
        try { // Noncompliant - empty try block
        } catch (Exception e) {
            System.debug(e.getMessage());
        }
    }

    public void process() {
        try { // Noncompliant - empty try block
        } finally {
            cleanup();
        }
    }
    private void cleanup() { }
}
```

## Compliant solution

```apex
public class SafeExecutor {
    public void execute() {
        try {
            performWork();
        } catch (Exception e) {
            System.debug(e.getMessage());
        }
    }

    public void process() {
        try {
            performWork();
        } finally {
            cleanup();
        }
    }
    private void performWork() { }
    private void cleanup() { }
}
```
