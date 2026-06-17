# Annotation does not exist in Apex

`qa-error-nonexistent-annotation` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

This rule detects annotations that are not part of the Apex language, such as @Override, @Nullable, or other Java annotations that developers may carry over by mistake. Apex has a fixed set of supported annotations (@AuraEnabled, @IsTest, @Future, etc.). Using an unsupported annotation causes compilation failures or is silently ignored, masking the developer's intent.

## Noncompliant code example

```apex
public class OrderService {

    @Override // Noncompliant — @Override does not exist in Apex
    public void processOrder(Order o) {
        o.Status = 'Shipped';
        update o;
    }

    @Nullable // Noncompliant — @Nullable does not exist in Apex
    public Account findAccount(String name) {
        List<Account> accts = [SELECT Id FROM Account WHERE Name = :name LIMIT 1];
        return accts.isEmpty() ? null : accts[0];
    }
}
```

## Compliant solution

```apex
public class OrderService {

    public void processOrder(Order o) {
        o.Status = 'Shipped';
        update o;
    }

    public Account findAccount(String name) {
        List<Account> accts = [SELECT Id FROM Account WHERE Name = :name LIMIT 1];
        return accts.isEmpty() ? null : accts[0];
    }
}
```
