# Avoid explicitly assigning null to variables

`qa-design-no-null-assignment` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Explicitly assigning null to a variable increases the risk of NullPointerException later when the variable is used without a null check. In most cases, null assignment can be avoided by using the Null Object pattern, returning empty collections, or restructuring the code to eliminate the need for a sentinel value.

## Noncompliant code example

```apex
public class AccountLookup {
    public Account findAccount(String name) {
        Account result = null; // Noncompliant - explicit null assignment
        for (Account a : [SELECT Id, Name FROM Account WHERE Name = :name]) {
            result = a;
        }
        return result;
    }

    public void resetState() {
        String cached = null; // Noncompliant - explicit null assignment
        Integer count = null; // Noncompliant - explicit null assignment
    }
}
```

## Compliant solution

```apex
public class AccountLookup {
    public Account findAccount(String name) {
        List<Account> accounts = [SELECT Id, Name FROM Account WHERE Name = :name LIMIT 1];
        return accounts.isEmpty() ? new Account() : accounts[0];
    }

    public void resetState() {
        String cached = '';
        Integer count = 0;
    }
}
```
