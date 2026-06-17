# Lightning Aura controller name must follow convention

`qa-convention-aura-controller-naming` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Lightning Aura controller classes should follow a naming convention such as ending with Controller. Without this convention, it is difficult for developers to distinguish server-side controllers from utility or service classes. Consistent naming makes the relationship between Aura components and their controllers immediately clear.

## Noncompliant code example

```apex
public with sharing class AccountData { // Noncompliant - should end with Controller
    @AuraEnabled
    public static List<Account> getAccounts() {
        return [SELECT Id, Name FROM Account LIMIT 50];
    }

    @AuraEnabled
    public static Account getAccountById(Id accountId) {
        return [SELECT Id, Name FROM Account WHERE Id = :accountId];
    }
}
```

## Compliant solution

```apex
public with sharing class AccountDataController {
    @AuraEnabled
    public static List<Account> getAccounts() {
        return [SELECT Id, Name FROM Account LIMIT 50];
    }

    @AuraEnabled
    public static Account getAccountById(Id accountId) {
        return [SELECT Id, Name FROM Account WHERE Id = :accountId];
    }
}
```
