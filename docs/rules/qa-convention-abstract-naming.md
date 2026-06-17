# Enforce naming standards for abstract classes

`qa-convention-abstract-naming` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Abstract classes should follow a consistent naming convention such as prefixing with Abstract or Base. When abstract classes are named like concrete classes, developers cannot tell at a glance whether a class is meant to be instantiated or extended. Consistent naming reduces confusion during code reviews and helps new team members navigate the codebase faster.

## Noncompliant code example

```apex
public abstract class AccountHelper { // Noncompliant - not prefixed with Abstract or Base
    private String region;

    public AccountHelper(String region) {
        this.region = region;
    }

    public abstract void process();
    public abstract List<Account> query();
}

public abstract class OpportunityUtils { // Noncompliant - not prefixed with Abstract or Base
    public abstract void validate();
}
```

## Compliant solution

```apex
public abstract class AbstractAccountHelper {
    private String region;

    public AbstractAccountHelper(String region) {
        this.region = region;
    }

    public abstract void process();
    public abstract List<Account> query();
}

public abstract class BaseOpportunityUtils {
    public abstract void validate();
}
```
