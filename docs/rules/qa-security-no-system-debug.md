# System.debug() must be removed from production code

`qa-security-no-system-debug` &middot; Security &middot; Security Hotspot &middot; severity CRITICAL &middot; enabled in the recommended profile

System.debug() statements left in production code write sensitive information — user IDs, record data, tokens, or query results — to debug logs that are accessible to administrators and support users. This creates an information exposure risk and degrades performance under heavy log volume. Remove debug statements or replace them with a guarded custom logging framework.

## Noncompliant code example

```apex
public class OrderService {
    public void processOrder(Order__c order) {
        System.debug('Processing order: ' + order);            // Noncompliant
        System.debug('User session: ' + UserInfo.getSessionId()); // Noncompliant
        Decimal total = calculateTotal(order);
        System.debug('Total calculated: ' + total);            // Noncompliant
        insert order;
    }
}
```

## Compliant solution

```apex
public class OrderService {
    public void processOrder(Order__c order) {
        Decimal total = calculateTotal(order);
        insert order;
        Logger.info('Order processed', new Map<String, Object>{
            'orderId' => order.Id
        });
    }
}
```
