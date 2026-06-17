# Catch blocks should not use instanceof to differentiate exceptions

`qa-error-handling-no-instanceof-in-catch` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Using instanceof checks inside a catch block to differentiate exception types defeats the purpose of structured exception handling. Each exception type should be caught in its own catch clause, which is clearer and avoids the risk of missing a type entirely.

## Noncompliant code example

```apex
public class ErrorRouter {
    public void process() {
        try {
            callExternalService();
        } catch (Exception e) {
            if (e instanceof DmlException) { // Noncompliant - use separate catch
                handleDml((DmlException) e);
            } else if (e instanceof CalloutException) { // Noncompliant
                handleCallout((CalloutException) e);
            }
        }
    }
    private void callExternalService() { }
    private void handleDml(DmlException e) { }
    private void handleCallout(CalloutException e) { }
}
```

## Compliant solution

```apex
public class ErrorRouter {
    public void process() {
        try {
            callExternalService();
        } catch (DmlException e) {
            handleDml(e);
        } catch (CalloutException e) {
            handleCallout(e);
        }
    }
    private void callExternalService() { }
    private void handleDml(DmlException e) { }
    private void handleCallout(CalloutException e) { }
}
```
