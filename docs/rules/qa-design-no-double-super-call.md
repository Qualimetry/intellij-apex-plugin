# Constructors must not invoke super() more than once

`qa-design-no-double-super-call` &middot; Design &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Invoking super() more than once in a constructor is illogical and confusing. The parent constructor should be called exactly once to establish the parent class state. Multiple calls suggest a misunderstanding of the constructor chain and can lead to unexpected initialization behavior.

## Noncompliant code example

```apex
public class PremiumAccount extends Account {
    public PremiumAccount() {
        super();
        super(); // Noncompliant - duplicate super() call
    }
}

public class GoldAccount extends Account {
    public GoldAccount(String name) {
        super();
        super(); // Noncompliant - duplicate super() call
        this.Name = name;
    }
}
```

## Compliant solution

```apex
public class PremiumAccount extends Account {
    public PremiumAccount() {
        super();
    }
}

public class GoldAccount extends Account {
    public GoldAccount(String name) {
        super();
        this.Name = name;
    }
}
```
