# Use isEmpty() for collection emptiness

`qa-performance-use-collection-isempty` &middot; Performance &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Checking collection emptiness with list.size() == 0 or list.size() > 0 is less expressive than list.isEmpty(). The isEmpty() method clearly communicates intent and avoids potential confusion between size checks and null checks.

## Noncompliant code example

```apex
public class OrderProcessor {
    public void process(List<Order__c> orders) {
        if (orders.size() == 0) {                    // Noncompliant
            return;
        }
        if (orders.size() > 0) {                     // Noncompliant
            processOrders(orders);
        }
    }
}
```

## Compliant solution

```apex
public class OrderProcessor {
    public void process(List<Order__c> orders) {
        if (orders.isEmpty()) {
            return;
        }
        if (!orders.isEmpty()) {
            processOrders(orders);
        }
    }
}
```
