# Classes and public methods should have ApexDoc

`qa-documentation-apexdoc-required` &middot; Documentation &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

This rule requires classes and interfaces to have ApexDoc comments (/** ... */). ApexDoc comments serve as the primary source of documentation for API consumers and are parsed by documentation generators. Without them, developers must read implementation details to understand a class's purpose, increasing onboarding time and the risk of misuse.

## Noncompliant code example

```apex
public class AccountService { // Noncompliant — missing ApexDoc
    public static List<Account> getActiveAccounts() {
        return [SELECT Id, Name FROM Account WHERE IsActive__c = true];
    }

    public static void deactivateAccount(Id accountId) {
        Account a = [SELECT Id FROM Account WHERE Id = :accountId];
        a.IsActive__c = false;
        update a;
    }
}
```

## Compliant solution

```apex
/**
 * Provides services for managing Account records including
 * retrieval and lifecycle operations.
 */
public class AccountService {
    /** Returns all active accounts in the org. */
    public static List<Account> getActiveAccounts() {
        return [SELECT Id, Name FROM Account WHERE IsActive__c = true];
    }

    /** Deactivates the account with the given ID. */
    public static void deactivateAccount(Id accountId) {
        Account a = [SELECT Id FROM Account WHERE Id = :accountId];
        a.IsActive__c = false;
        update a;
    }
}
```
