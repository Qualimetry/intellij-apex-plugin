# Do not wrap an exception in a new instance of the same type

`qa-error-handling-no-same-exception-wrap` &middot; Error Handling &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Wrapping an exception in a new instance of the same exception type adds no information and obscures the original stack trace. If additional context is needed, use a different exception type or add a message; otherwise, simply rethrow the original exception.

## Noncompliant code example

```apex
public class ApiClient {
    public void callService() {
        try {
            doCallout();
        } catch (CalloutException e) {
            throw new CalloutException(e.getMessage()); // Noncompliant - same type
        }
    }

    public void processData() {
        try {
            parseResponse();
        } catch (JSONException e) {
            throw new JSONException(e.getMessage()); // Noncompliant - same type
        }
    }
    private void doCallout() { }
    private void parseResponse() { }
}
```

## Compliant solution

```apex
public class ApiClient {
    public void callService() {
        try {
            doCallout();
        } catch (CalloutException e) {
            throw new ApplicationException('Service call failed', e);
        }
    }

    public void processData() {
        try {
            parseResponse();
        } catch (JSONException e) {
            throw e;
        }
    }
    private void doCallout() { }
    private void parseResponse() { }
}
```
