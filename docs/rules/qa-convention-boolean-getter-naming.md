# Boolean getters should use 'is' prefix convention

`qa-convention-boolean-getter-naming` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Boolean getter methods should follow the isXxx naming convention rather than getXxx. The is prefix reads more naturally in conditions (e.g. if (account.isActive())) and is the established convention in most object-oriented languages. Consistent naming helps developers predict method names without consulting documentation.

## Noncompliant code example

```apex
public class AccountWrapper {
    private Boolean active;
    private Boolean enabled;

    public Boolean getActive() { // Noncompliant - should be isActive
        return active;
    }

    public Boolean getEnabled() { // Noncompliant - should be isEnabled
        return enabled;
    }
}
```

## Compliant solution

```apex
public class AccountWrapper {
    private Boolean active;
    private Boolean enabled;

    public Boolean isActive() {
        return active;
    }

    public Boolean isEnabled() {
        return enabled;
    }
}
```
