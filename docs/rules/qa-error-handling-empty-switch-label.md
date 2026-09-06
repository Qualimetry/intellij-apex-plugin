# Switch case labels must contain at least one statement

`qa-error-handling-empty-switch-label` &middot; Error Handling &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

A when clause in a switch statement that has no body silently ignores the matched value. This often indicates missing implementation. If the intent is to fall through to another case, combine the cases explicitly.

## Noncompliant code example

```apex
public class StatusRouter {
    public void route(String status) {
        switch on status {
            when 'Draft' { // Noncompliant - empty case body
            }
            when 'Active' {
                processActive();
            }
            when 'Closed' { // Noncompliant - empty case body
            }
            when else {
                handleDefault();
            }
        }
    }
    private void processActive() { }
    private void handleDefault() { }
}
```

## Compliant solution

```apex
public class StatusRouter {
    public void route(String status) {
        switch on status {
            when 'Draft' {
                processDraft();
            }
            when 'Active' {
                processActive();
            }
            when 'Closed' {
                processClosed();
            }
            when else {
                handleDefault();
            }
        }
    }
    private void processDraft() { }
    private void processActive() { }
    private void processClosed() { }
    private void handleDefault() { }
}
```
