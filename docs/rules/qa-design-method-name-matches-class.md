# Methods must not share the name of their enclosing class

`qa-design-method-name-matches-class` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A method that shares its name with the enclosing class looks like a constructor but is not one. This confuses developers who may expect constructor semantics (no return type, called on instantiation). Rename the method to clearly describe its behavior.

## Noncompliant code example

```apex
public class AccountValidator {
    public Boolean AccountValidator(Account acc) { // Noncompliant - looks like a constructor
        return acc.Name != null;
    }

    public void process() {
        Account a = new Account(Name = 'Test');
        AccountValidator(a);
    }
}
```

## Compliant solution

```apex
public class AccountValidator {
    public Boolean validate(Account acc) {
        return acc.Name != null;
    }

    public void process() {
        Account a = new Account(Name = 'Test');
        validate(a);
    }
}
```
