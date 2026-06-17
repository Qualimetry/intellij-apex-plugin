# Controller constructors/actions must not perform DML

`qa-security-no-dml-in-controller-init` &middot; Security &middot; Security Hotspot &middot; severity MAJOR &middot; enabled in the recommended profile

Performing DML operations (insert, update, delete) in a Visualforce controller constructor or page action method is dangerous because these execute on every page load, including refreshes and back-button navigation. This can cause unintended data modifications, governor limit exhaustion, and CSRF vulnerabilities since page loads are not protected by CSRF tokens.

## Noncompliant code example

```apex
public class AccountController {
    public Account account { get; set; }

    public AccountController() {
        account = new Account(Name = 'Auto-Created');
        insert account;  // Noncompliant — DML in constructor
    }

    public PageReference init() {
        delete [SELECT Id FROM Log__c WHERE CreatedDate < :Date.today()];  // Noncompliant
        return null;
    }
}
```

## Compliant solution

```apex
public class AccountController {
    public Account account { get; set; }

    public AccountController() {
        account = new Account(Name = 'Draft');
    }

    public PageReference saveAccount() {
        insert account;
        return new PageReference('/' + account.Id);
    }
}
```
