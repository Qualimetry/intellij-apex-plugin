# Public members must include documentation comments

`qa-convention-comment-required` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Public and global methods and classes form the API surface of your code. Without documentation comments, consumers must read the implementation to understand behavior, parameters, and return values. Adding brief doc comments above public members reduces onboarding time and makes code reviews more productive.

## Noncompliant code example

```apex
public class AccountService {
    public void createAccount(String name) { // Noncompliant - no doc comment
        Account acc = new Account(Name = name);
        insert acc;
    }

    public List<Account> findByName(String name) {
        return [SELECT Id FROM Account WHERE Name = :name];
    }
}
```

## Compliant solution

```apex
public class AccountService {
    /** Creates a new Account record with the given name. */
    public void createAccount(String name) {
        Account acc = new Account(Name = name);
        insert acc;
    }

    /** Finds accounts matching the provided name. */
    public List<Account> findByName(String name) {
        return [SELECT Id FROM Account WHERE Name = :name];
    }
}
```
