# Classes should define at least one explicit constructor

`qa-design-require-constructor` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Classes that rely on the implicit default constructor make initialization invisible and error-prone. An explicit constructor documents required setup steps, enforces valid initial state, and makes the class easier to extend. Without one, callers may create instances in an incomplete or invalid state.

## Noncompliant code example

```apex
public class AccountService { // Noncompliant - no explicit constructor
    private String orgId;
    private Boolean isActive;

    public void activate() {
        isActive = true;
    }
}
```

## Compliant solution

```apex
public class AccountService {
    private String orgId;
    private Boolean isActive;

    public AccountService() {
        this.orgId = UserInfo.getOrganizationId();
        this.isActive = false;
    }

    public void activate() {
        isActive = true;
    }
}
```
