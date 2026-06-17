# Declarations must not shadow outer fields or variables

`qa-convention-no-shadowed-declaration` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

When a local variable or method parameter has the same name as an enclosing field, the local declaration shadows the field. This makes code harder to reason about because the reader cannot tell whether a reference targets the field or the local variable without checking scope carefully. Bugs arise when a developer intends to update the field but accidentally writes to the local instead.

## Noncompliant code example

```apex
public class AccountManager {
    private String name;
    private Integer count;

    public void updateName(String name) { // Noncompliant - parameter shadows field 'name'
        this.name = name;
    }

    public void calculate() {
        Integer count = 0; // Noncompliant - local shadows field 'count'
        for (Account a : accounts) {
            count++;
        }
    }
}
```

## Compliant solution

```apex
public class AccountManager {
    private String name;
    private Integer count;

    public void updateName(String newName) {
        this.name = newName;
    }

    public void calculate() {
        Integer runningTotal = 0;
        for (Account a : accounts) {
            runningTotal++;
        }
    }
}
```
