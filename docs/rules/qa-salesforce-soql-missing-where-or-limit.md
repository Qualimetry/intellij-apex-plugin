# SOQL queries must include WHERE or LIMIT

`qa-salesforce-soql-missing-where-or-limit` &middot; Salesforce &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

SOQL queries without a WHERE clause or LIMIT retrieve all records of the queried object, which can cause full table scans, exhaust the 50,000-row query limit, and severely degrade performance in orgs with large data volumes. Always scope queries with a WHERE clause to filter results or a LIMIT to cap the row count.

## Noncompliant code example

```apex
public class AccountLoader {
    public List<Account> loadAll() {
        return [SELECT Id, Name, Industry FROM Account];   // Noncompliant — no WHERE or LIMIT
    }

    public List<Contact> getContacts() {
        return [SELECT Id, FirstName, LastName
                FROM Contact
                ORDER BY LastName];                         // Noncompliant
    }
}
```

## Compliant solution

```apex
public class AccountLoader {
    public List<Account> loadActive() {
        return [SELECT Id, Name, Industry FROM Account
                WHERE IsActive__c = true LIMIT 200];
    }

    public List<Contact> getContacts(Id accountId) {
        return [SELECT Id, FirstName, LastName
                FROM Contact
                WHERE AccountId = :accountId
                ORDER BY LastName];
    }
}
```
