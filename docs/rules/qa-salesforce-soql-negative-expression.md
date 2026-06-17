# SOQL queries should not use negative filter expressions

`qa-salesforce-soql-negative-expression` &middot; Salesforce &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

SOQL queries with negative filter expressions (NOT, !=, NOT IN, excludes) often bypass index optimizations, leading to full table scans that consume excessive query time and can hit governor limits on large data volumes. Rewrite queries to use positive filters where possible, or add selective indexed fields to the WHERE clause.

## Noncompliant code example

```apex
public class AccountFilter {
    public List<Account> getActiveAccounts() {
        return [SELECT Id, Name FROM Account
                WHERE Status__c != 'Closed'];       // Noncompliant — negative filter
    }

    public List<Contact> filterContacts() {
        return [SELECT Id FROM Contact
                WHERE Department NOT IN ('HR', 'Finance')];  // Noncompliant
    }
}
```

## Compliant solution

```apex
public class AccountFilter {
    public List<Account> getActiveAccounts() {
        return [SELECT Id, Name FROM Account
                WHERE Status__c IN ('Open', 'Pending', 'Active')];
    }

    public List<Contact> filterContacts() {
        return [SELECT Id FROM Contact
                WHERE Department IN ('Engineering', 'Sales', 'Marketing')];
    }
}
```
