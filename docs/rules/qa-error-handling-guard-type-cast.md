# Type casts must be guarded by instanceof checks

`qa-error-handling-guard-type-cast` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Casting an object to a specific type without first verifying with instanceof risks a TypeException at runtime if the object is not of the expected type. Always guard casts with an instanceof check to fail gracefully instead of crashing.

## Noncompliant code example

```apex
public class EventProcessor {
    public void process(Object event) {
        Account acc = (Account) event; // Noncompliant - no instanceof guard
        System.debug(acc.Name);
    }

    public void handle(SObject record) {
        Contact con = (Contact) record; // Noncompliant - no instanceof guard
        System.debug(con.Email);
    }
}
```

## Compliant solution

```apex
public class EventProcessor {
    public void process(Object event) {
        if (event instanceof Account) {
            Account acc = (Account) event;
            System.debug(acc.Name);
        }
    }

    public void handle(SObject record) {
        if (record instanceof Contact) {
            Contact con = (Contact) record;
            System.debug(con.Email);
        }
    }
}
```
