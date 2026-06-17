# Declare only one variable per statement

`qa-convention-one-declaration-per-line` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Declaring multiple variables on a single line makes it harder to see what is being declared and complicates debugging and version control diffs. Each declaration on its own line ensures clarity and makes it easier to add, remove, or comment out individual variables.

## Noncompliant code example

```apex
public class OrderProcessor {
    public void process() {
        Integer count = 0, total = 0; // Noncompliant - two declarations on one line
        String name = 'test';
        Boolean active = true;

        Decimal price = 0, tax = 0; // Noncompliant - two declarations on one line
        System.debug(count + total);
    }
}
```

## Compliant solution

```apex
public class OrderProcessor {
    public void process() {
        Integer count = 0;
        Integer total = 0;
        String name = 'test';
        Boolean active = true;

        Decimal price = 0;
        Decimal tax = 0;
        System.debug(count + total);
    }
}
```
