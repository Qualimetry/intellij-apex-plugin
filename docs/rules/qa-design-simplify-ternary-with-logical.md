# Simplify ternary expressions using logical operators

`qa-design-simplify-ternary-with-logical` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Ternary expressions that return boolean literals can be simplified using logical operators. For example, condition ? true : false is equivalent to condition, and condition ? false : true is equivalent to !condition. The simplified form is shorter and more readable.

## Noncompliant code example

```apex
public class ValidationHelper {
    public Boolean isValid(String value) {
        return value != null ? true : false; // Noncompliant - simplify to value != null
    }

    public Boolean isEmpty(List<Account> accounts) {
        return accounts.size() == 0 ? true : false; // Noncompliant - simplify to accounts.size() == 0
    }
}
```

## Compliant solution

```apex
public class ValidationHelper {
    public Boolean isValid(String value) {
        return value != null;
    }

    public Boolean isEmpty(List<Account> accounts) {
        return accounts.isEmpty();
    }
}
```
