# Place field declarations at the top of the class

`qa-convention-field-declaration-position` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Placing field declarations after method declarations breaks the expected reading order of a class. Developers scanning a class expect to see its data (fields) before its behavior (methods). Interleaving fields among methods forces the reader to hunt for state, making the class harder to understand at a glance.

## Noncompliant code example

```apex
public class AccountService {
    public void process() { // Noncompliant - fields declared after methods
        doWork();
    }

    private String name;
    private Integer count;

    private void doWork() { }
}
```

## Compliant solution

```apex
public class AccountService {
    private String name;
    private Integer count;

    public void process() {
        doWork();
    }

    private void doWork() { }
}
```
