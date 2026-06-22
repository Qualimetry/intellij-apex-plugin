# Check CRUD permissions before DML

`qa-security-crud-violation` &middot; Security &middot; Security Hotspot &middot; severity CRITICAL &middot; enabled in the recommended profile

This rule detects DML operations (insert, update, delete, upsert) that are not preceded by CRUD permission checks. Without verifying isCreateable(), isUpdateable(), or isDeletable(), the code may modify records that the current user should not have access to, violating the principle of least privilege and potentially exposing sensitive data.

## Noncompliant code example

```apex
public class AccountManager {
    public void createAccounts(List<Account> accounts) {
        insert accounts; // Noncompliant — no CRUD check
    }

    public void removeAccount(Id accountId) {
        Account a = [SELECT Id FROM Account WHERE Id = :accountId];
        delete a; // Noncompliant — no CRUD check
    }

    public void bulkUpsert(List<Contact> contacts) {
        Database.upsert(contacts, false); // Noncompliant — no CRUD check
    }
}
```

## Compliant solution

```apex
public class AccountManager {
    public void createAccounts(List<Account> accounts) {
        if (Schema.sObjectType.Account.isCreateable()) {
            insert accounts;
        } else {
            throw new SecurityException('Insufficient create permissions on Account');
        }
    }

    public void removeAccount(Id accountId) {
        if (Schema.sObjectType.Account.isDeletable()) {
            Account a = [SELECT Id FROM Account WHERE Id = :accountId];
            delete a;
        }
    }
}
```
