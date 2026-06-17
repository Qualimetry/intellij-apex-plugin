# SOQL should use COUNT() instead of .size()

`qa-salesforce-soql-count-preferred` &middot; Salesforce &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags code that queries records and then calls .size() to count them, instead of using COUNT() directly in SOQL. Querying all records just to count them wastes heap space and query row limits. Use [SELECT COUNT() FROM ...] for efficient counting. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class AccountCounter {
    public Integer getActiveCount() {
        List<Account> accounts = [SELECT Id FROM Account WHERE IsActive__c = true];
        return accounts.size(); // Noncompliant — inefficient count
    }
}
```

## Compliant solution

```apex
public class AccountCounter {
    public Integer getActiveCount() {
        return [SELECT COUNT() FROM Account WHERE IsActive__c = true];
    }
}
```
