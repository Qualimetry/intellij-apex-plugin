# Remove variables used only in an immediate return

`qa-design-unnecessary-local-before-return` &middot; Design &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Declaring a local variable only to return it on the very next line adds no clarity. The variable serves no debugging purpose and clutters the code. Returning the expression directly is more concise without sacrificing readability.

## Noncompliant code example

```apex
public class AccountService {
    public Account getAccount(Id accountId) {
        Account result = [SELECT Id, Name FROM Account WHERE Id = :accountId]; // Noncompliant
        return result;
    }

    public Integer getCount() {
        Integer count = [SELECT COUNT() FROM Contact]; // Noncompliant
        return count;
    }
}
```

## Compliant solution

```apex
public class AccountService {
    public Account getAccount(Id accountId) {
        return [SELECT Id, Name FROM Account WHERE Id = :accountId];
    }

    public Integer getCount() {
        return [SELECT COUNT() FROM Contact];
    }
}
```
