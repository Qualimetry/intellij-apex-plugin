# Method parameter names should not use prefixes

`qa-convention-no-parameter-prefix` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Method parameter names should not use Hungarian-style prefixes such as p_, a_, or the. These prefixes were common in older languages but add noise without improving clarity in modern Apex code. Modern IDEs distinguish parameters from fields through syntax highlighting, making prefixes unnecessary.

## Noncompliant code example

```apex
public class AccountService {
    public void createAccount(String p_name, String p_industry) { // Noncompliant - p_ prefix
        Account acc = new Account(Name = p_name, Industry = p_industry);
        insert acc;
    }

    public void updateStatus(String a_status) { // Noncompliant - a_ prefix
        this.status = a_status;
    }
}
```

## Compliant solution

```apex
public class AccountService {
    public void createAccount(String name, String industry) {
        Account acc = new Account(Name = name, Industry = industry);
        insert acc;
    }

    public void updateStatus(String status) {
        this.status = status;
    }
}
```
