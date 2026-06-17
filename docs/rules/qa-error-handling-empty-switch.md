# Switch blocks must contain at least one case

`qa-error-handling-empty-switch` &middot; Error Handling &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

A switch statement with no when clauses serves no purpose. The expression is evaluated but no action is taken. Either add the intended cases or remove the empty switch.

## Noncompliant code example

```apex
public class EventRouter {
    public void route(String eventType) {
        switch on eventType { // Noncompliant - no cases
        }
    }

    public void dispatch(Integer code) {
        switch on code { // Noncompliant - no cases
        }
    }
}
```

## Compliant solution

```apex
public class EventRouter {
    public void route(String eventType) {
        switch on eventType {
            when 'Click' {
                handleClick();
            }
            when else {
                handleDefault();
            }
        }
    }
    private void handleClick() { }
    private void handleDefault() { }
}
```
