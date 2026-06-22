# Return empty collections instead of null

`qa-design-return-empty-collection` &middot; Design &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Returning null from a method that has a collection return type forces every caller to add a null check before using the result. Returning an empty collection instead eliminates this burden and prevents NullPointerException, following the principle of least surprise.

## Noncompliant code example

```apex
public class ContactRepository {
    public List<Contact> findByEmail(String email) {
        if (String.isBlank(email)) {
            return null; // Noncompliant - return empty list instead
        }
        return [SELECT Id, Name FROM Contact WHERE Email = :email];
    }

    public List<String> getTags(Account acc) {
        if (acc == null) {
            return null; // Noncompliant - return empty list instead
        }
        return acc.Tags__c.split(',');
    }
}
```

## Compliant solution

```apex
public class ContactRepository {
    public List<Contact> findByEmail(String email) {
        if (String.isBlank(email)) {
            return new List<Contact>();
        }
        return [SELECT Id, Name FROM Contact WHERE Email = :email];
    }

    public List<String> getTags(Account acc) {
        if (acc == null) {
            return new List<String>();
        }
        return acc.Tags__c.split(',');
    }
}
```
