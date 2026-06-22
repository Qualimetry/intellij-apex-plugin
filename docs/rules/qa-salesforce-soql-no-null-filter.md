# SOQL WHERE should not filter on null values

`qa-salesforce-soql-no-null-filter` &middot; Salesforce &middot; Code Smell &middot; severity INFO &middot; enabled in the recommended profile

Filtering SOQL queries with = null or != null in WHERE clauses is often inefficient because null-value indexes are not selective on most Salesforce objects. This can lead to full table scans on large data volumes. Consider using a sentinel value, a formula field, or restructuring the query to use selective indexed fields instead.

## Noncompliant code example

```apex
public class LeadCleanup {
    public List<Lead> getUnassigned() {
        return [SELECT Id, Name FROM Lead
                WHERE OwnerId = null];               // Noncompliant — null filter
    }

    public List<Contact> getWithEmail() {
        return [SELECT Id FROM Contact
                WHERE Email != null];                 // Noncompliant
    }
}
```

## Compliant solution

```apex
public class LeadCleanup {
    public List<Lead> getUnassigned() {
        return [SELECT Id, Name FROM Lead
                WHERE IsAssigned__c = false LIMIT 200];
    }

    public List<Contact> getWithEmail() {
        return [SELECT Id FROM Contact
                WHERE HasEmail__c = true LIMIT 200];
    }
}
```
