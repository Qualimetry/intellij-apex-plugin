# Field names must not match the enclosing type name

`qa-convention-field-name-matches-type` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

A field whose name matches its enclosing class creates confusion, especially in constructors and methods where this.ClassName reads awkwardly. It also hampers readability when scanning code because the same identifier refers to two very different things: the type and an instance variable. Choose a descriptive name that conveys the field's purpose.

## Noncompliant code example

```apex
public class AccountService {
    private AccountService AccountService; // Noncompliant - field name matches class name

    public void setDelegate(AccountService svc) {
        this.AccountService = svc;
    }

    public void run() {
        AccountService.process();
    }
}
```

## Compliant solution

```apex
public class AccountService {
    private AccountService delegate;

    public void setDelegate(AccountService svc) {
        this.delegate = svc;
    }

    public void run() {
        delegate.process();
    }
}
```
