# @AuraEnabled getter must be public

`qa-error-inaccessible-aura-getter` &middot; Error Handling &middot; Code Smell &middot; severity CRITICAL &middot; enabled in the recommended profile

This rule flags @AuraEnabled members that are not declared public or global. The Lightning framework requires @AuraEnabled properties and methods to be publicly accessible so they can be serialized and invoked from Lightning components. A non-public @AuraEnabled member compiles but silently fails at runtime, producing confusing errors in the client.

## Noncompliant code example

```apex
public class ContactController {
    @AuraEnabled
    private String contactName; // Noncompliant — private

    @AuraEnabled
    static List<Contact> getContacts() { // Noncompliant — default access
        return [SELECT Id, Name FROM Contact LIMIT 10];
    }
}
```

## Compliant solution

```apex
public class ContactController {
    @AuraEnabled
    public String contactName;

    @AuraEnabled
    public static List<Contact> getContacts() {
        return [SELECT Id, Name FROM Contact LIMIT 10];
    }
}
```
