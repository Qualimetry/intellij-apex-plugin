# Classes performing DML must specify sharing declaration

`qa-security-sharing-on-dml-class` &middot; Security &middot; Security Hotspot &middot; severity MAJOR &middot; optional

This rule flags classes that perform DML operations without an explicit sharing declaration. DML in a class running without sharing can modify records the current user should not access. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class AccountUpdater { // Noncompliant — DML without sharing
    public void updateAccounts(List<Account> accounts) {
        update accounts;
    }
}
```

## Compliant solution

```apex
public with sharing class AccountUpdater {
    public void updateAccounts(List<Account> accounts) {
        update accounts;
    }
}
```
