# All code paths must be reachable

`qa-error-handling-unreachable-code` &middot; Error Handling &middot; Bug &middot; severity MAJOR &middot; optional

This rule flags code that appears after unconditional return, throw, or break statements. Unreachable code is never executed, suggesting a logic error or leftover debugging code that should be removed. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class OrderService {
    public String getStatus(Id orderId) {
        return 'Pending';
        System.debug('checking order'); // Noncompliant — unreachable
    }

    public void cancel(Id orderId) {
        throw new OrderException('Cannot cancel');
        update new Order(Id = orderId, Status = 'Cancelled'); // Noncompliant
    }
}
```

## Compliant solution

```apex
public class OrderService {
    public String getStatus(Id orderId) {
        System.debug('checking order');
        return 'Pending';
    }

    public void cancel(Id orderId) {
        update new Order(Id = orderId, Status = 'Cancelled');
    }
}
```
