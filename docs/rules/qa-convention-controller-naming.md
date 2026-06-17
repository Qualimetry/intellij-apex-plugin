# Controller classes must follow naming convention

`qa-convention-controller-naming` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Visualforce and Aura controller classes should follow a naming convention such as ending with Controller. Without this convention, developers reviewing page markup or component bundles cannot quickly identify which Apex class handles the server-side logic. Consistent naming also helps automated tooling and documentation generators associate controllers with their corresponding UI components.

## Noncompliant code example

```apex
public class AccountPage { // Noncompliant - should end with Controller
    public PageReference save() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        return null;
    }

    public PageReference cancel() {
        return new PageReference('/home');
    }
}
```

## Compliant solution

```apex
public class AccountPageController {
    public PageReference save() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        return null;
    }

    public PageReference cancel() {
        return new PageReference('/home');
    }
}
```
