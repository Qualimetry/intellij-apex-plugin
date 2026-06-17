# Ternary expressions should not use negated conditions

`qa-design-no-negated-ternary` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Ternary expressions with negated conditions force the reader to mentally invert the logic, making the code harder to understand at a glance. Swapping the branches and using a positive condition produces equivalent logic that reads more naturally.

## Noncompliant code example

```apex
public class NotificationService {
    public String getLabel(Boolean isActive) {
        return isActive ? !isActive ? 'Inactive' : 'Active' : 'Unknown'; // Noncompliant
    }

    public String getStatus(Boolean flag) {
        return flag ? !flag ? 'off' : 'on' : 'n/a'; // Noncompliant
    }
}
```

## Compliant solution

```apex
public class NotificationService {
    public String getLabel(Boolean isActive) {
        if (isActive) {
            return 'Active';
        }
        return 'Inactive';
    }

    public String getStatus(Boolean flag) {
        return flag ? 'on' : 'off';
    }
}
```
