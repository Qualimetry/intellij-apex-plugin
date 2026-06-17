# Local variables that are not reassigned should be final

`qa-convention-local-variable-should-be-final` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Local variables that are assigned once and never reassigned can be declared final to signal that their value is intentionally constant within the method. This makes the code easier to reason about because the reader knows the value will not change after initialization. It also prevents accidental reassignment during future edits.

## Noncompliant code example

```apex
public class Calculator {
    public void calculate() {
        Integer x = 1; // Noncompliant - could be final
        Integer y = 2;
        System.debug(x + y);
    }

    public void validate() {
        Boolean valid = true;
        System.debug(valid);
    }
}
```

## Compliant solution

```apex
public class Calculator {
    public void calculate() {
        final Integer x = 1;
        final Integer y = 2;
        System.debug(x + y);
    }

    public void validate() {
        final Boolean valid = true;
        System.debug(valid);
    }
}
```
