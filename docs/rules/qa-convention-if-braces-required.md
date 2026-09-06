# if blocks must be enclosed in curly braces

`qa-convention-if-braces-required` &middot; Convention &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Single-statement if bodies without curly braces are error-prone. Adding a second statement later without remembering to add braces silently changes the control flow. Requiring braces on all if blocks prevents dangling-statement bugs and keeps formatting consistent across the codebase.

## Noncompliant code example

```apex
public class OrderService {
    public void process(Boolean active, String status) {
        if (active) doWork(); // Noncompliant - no braces

        if (status == null) return; // Noncompliant - no braces

        if (active) {
            validate();
        }
    }

    private void doWork() { }
    private void validate() { }
}
```

## Compliant solution

```apex
public class OrderService {
    public void process(Boolean active, String status) {
        if (active) {
            doWork();
        }

        if (status == null) {
            return;
        }

        if (active) {
            validate();
        }
    }

    private void doWork() { }
    private void validate() { }
}
```
