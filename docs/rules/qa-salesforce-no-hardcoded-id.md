# Salesforce record IDs must not be hardcoded

`qa-salesforce-no-hardcoded-id` &middot; Salesforce &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags hardcoded Salesforce record IDs in Apex code. Record IDs are environment-specific and differ between sandboxes, scratch orgs, and production. Hardcoding them causes failures during deployment. Use queries, Custom Metadata, or Custom Settings to look up record IDs dynamically. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class RecordTypeService {
    public Id getDefaultRecordType() {
        return '012000000000001AAA'; // Noncompliant — hardcoded ID
    }

    public void assignOwner(Account a) {
        a.OwnerId = '005000000000001AAA'; // Noncompliant
        update a;
    }
}
```

## Compliant solution

```apex
public class RecordTypeService {
    public Id getDefaultRecordType() {
        return Schema.SObjectType.Account.getRecordTypeInfosByDeveloperName()
            .get('Default').getRecordTypeId();
    }

    public void assignOwner(Account a) {
        User owner = [SELECT Id FROM User WHERE Alias = 'defaultowner' LIMIT 1];
        a.OwnerId = owner.Id;
        update a;
    }
}
```
