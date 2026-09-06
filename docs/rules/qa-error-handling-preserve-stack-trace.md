# Rethrown exceptions must preserve the original stack trace

`qa-error-handling-preserve-stack-trace` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

When rethrowing an exception, the original stack trace must be preserved. Creating a new exception without chaining the cause discards the call chain that led to the original failure, making production debugging extremely difficult.

## Noncompliant code example

```apex
public class IntegrationService {
    public void sync() {
        try {
            callExternalApi();
        } catch (CalloutException e) {
            throw new IntegrationException('Sync failed'); // Noncompliant - stack trace lost
        }
    }

    public void importData() {
        try {
            parseResponse();
        } catch (JSONException e) {
            throw new DataException('Parse error'); // Noncompliant - stack trace lost
        }
    }
    private void callExternalApi() { }
    private void parseResponse() { }
}
```

## Compliant solution

```apex
public class IntegrationService {
    public void sync() {
        try {
            callExternalApi();
        } catch (CalloutException e) {
            throw new IntegrationException('Sync failed: ' + e.getMessage());
        }
    }

    public void importData() {
        try {
            parseResponse();
        } catch (JSONException e) {
            throw new DataException('Parse error: ' + e.getMessage());
        }
    }
    private void callExternalApi() { }
    private void parseResponse() { }
}
```
