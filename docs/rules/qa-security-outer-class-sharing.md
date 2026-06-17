# Outer classes with SOQL or DML must declare sharing level

`qa-security-outer-class-sharing` &middot; Security &middot; Security Hotspot &middot; severity MAJOR &middot; optional

This rule flags outer classes that contain SOQL queries or DML statements but do not declare an explicit sharing level. Without a sharing declaration, these operations may bypass record-level security, potentially exposing or modifying data the user should not access. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class AccountQuery { // Noncompliant — no sharing declaration
    public List<Account> getAll() {
        return [SELECT Id, Name FROM Account];
    }
}
```

## Compliant solution

```apex
public with sharing class AccountQuery {
    public List<Account> getAll() {
        return [SELECT Id, Name FROM Account];
    }
}
```
