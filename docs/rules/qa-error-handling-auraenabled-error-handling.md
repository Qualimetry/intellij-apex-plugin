# AuraEnabled methods must implement structured error handling

`qa-error-handling-auraenabled-error-handling` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Methods annotated with @AuraEnabled are invoked from Lightning components and must implement structured error handling. If an unhandled exception escapes, the Lightning client receives a generic error message that provides no actionable information to the user. Wrapping the method body in a try-catch with an AuraHandledException ensures meaningful error messages reach the UI.

## Noncompliant code example

```apex
public class AccountController {
    @AuraEnabled
    public static Account getAccount(Id accountId) { // Noncompliant - no error handling
        return [SELECT Id, Name FROM Account WHERE Id = :accountId];
    }

    @AuraEnabled
    public static void updateName(Id accountId, String name) { // Noncompliant
        Account acc = [SELECT Id FROM Account WHERE Id = :accountId];
        acc.Name = name;
        update acc;
    }
}
```

## Compliant solution

```apex
public class AccountController {
    @AuraEnabled
    public static Account getAccount(Id accountId) {
        try {
            return [SELECT Id, Name FROM Account WHERE Id = :accountId];
        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled
    public static void updateName(Id accountId, String name) {
        try {
            Account acc = [SELECT Id FROM Account WHERE Id = :accountId];
            acc.Name = name;
            update acc;
        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }
}
```
