# SOQL and DML must not execute inside loops

`qa-salesforce-no-dml-soql-in-loop` &middot; Salesforce &middot; Bug &middot; severity CRITICAL &middot; optional

This rule detects SOQL queries and DML statements (insert, update, delete) executed inside loop bodies. Each iteration consumes governor limits, and even moderate record volumes can cause LimitException. Move queries and DML operations outside the loop and process records in bulk. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class ContactProcessor {
    public void updateContacts(List<Id> contactIds) {
        for (Id cId : contactIds) {
            Contact c = [SELECT Id, Name FROM Contact WHERE Id = :cId]; // Noncompliant
            c.Description = 'Reviewed';
            update c; // Noncompliant
        }
    }
}
```

## Compliant solution

```apex
public class ContactProcessor {
    public void updateContacts(List<Id> contactIds) {
        List<Contact> contacts = [SELECT Id, Name FROM Contact WHERE Id IN :contactIds];
        for (Contact c : contacts) {
            c.Description = 'Reviewed';
        }
        update contacts;
    }
}
```
