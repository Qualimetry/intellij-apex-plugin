# if/else blocks must use curly braces

`qa-convention-ifelse-braces-required` &middot; Convention &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Omitting curly braces around else and else if blocks makes the code fragile. When a developer later adds a second statement, they may forget to add braces, causing the new statement to execute unconditionally. Always using braces prevents this class of bugs and keeps the visual structure consistent.

## Noncompliant code example

```apex
public class OrderService {
    public void process(Boolean active) {
        if (active) {
            doWork();
        }
        else // Noncompliant - else without braces
            doNothing();

        if (active) {
            activate();
        }
        else // Noncompliant - else without braces
            deactivate();
    }
}
```

## Compliant solution

```apex
public class OrderService {
    public void process(Boolean active) {
        if (active) {
            doWork();
        } else {
            doNothing();
        }

        if (active) {
            activate();
        } else {
            deactivate();
        }
    }
}
```
