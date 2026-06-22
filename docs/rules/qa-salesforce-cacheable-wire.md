# @AuraEnabled methods for wire adapters should be cacheable

`qa-salesforce-cacheable-wire` &middot; Salesforce &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

This rule flags @AuraEnabled methods that are not marked as cacheable=true. Methods consumed by Lightning Web Component wire adapters should be cacheable so the framework can serve cached data without making redundant server round-trips. Without caching, every component render triggers a new server call, increasing latency and server load.

## Noncompliant code example

```apex
public with sharing class ContactController {
    @AuraEnabled // Noncompliant — missing cacheable=true
    public static List<Contact> getContacts() {
        return [SELECT Id, Name, Email FROM Contact LIMIT 50];
    }

    @AuraEnabled // Noncompliant — missing cacheable=true
    public static Contact getContactById(Id contactId) {
        return [SELECT Id, Name, Email, Phone FROM Contact WHERE Id = :contactId];
    }
}
```

## Compliant solution

```apex
public with sharing class ContactController {
    @AuraEnabled(cacheable=true)
    public static List<Contact> getContacts() {
        return [SELECT Id, Name, Email FROM Contact LIMIT 50];
    }

    @AuraEnabled(cacheable=true)
    public static Contact getContactById(Id contactId) {
        return [SELECT Id, Name, Email, Phone FROM Contact WHERE Id = :contactId];
    }
}
```
