# Apex classes must declare a sharing level

`qa-security-explicit-sharing-required` &middot; Security &middot; Security Hotspot &middot; severity MAJOR &middot; optional

This rule flags Apex classes that do not declare an explicit sharing level (with sharing, without sharing, or inherited sharing). Without an explicit declaration, the class defaults to without sharing in most contexts, potentially granting users access to records they should not see. Always declare sharing intent explicitly. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class AccountService { // Noncompliant — no sharing declaration
    public List<Account> getAllAccounts() {
        return [SELECT Id, Name FROM Account];
    }

    public void updateAccount(Account a) {
        update a;
    }
}
```

## Compliant solution

```apex
public with sharing class AccountService {
    public List<Account> getAllAccounts() {
        return [SELECT Id, Name FROM Account];
    }

    public void updateAccount(Account a) {
        update a;
    }
}
```
