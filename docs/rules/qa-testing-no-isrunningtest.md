# Production code should not branch on Test.isRunningTest()

`qa-testing-no-isrunningtest` &middot; Testing &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Branching on Test.isRunningTest() in production code creates divergent execution paths between tests and production, undermining test validity. Tests pass because they follow a different code path, while the actual production path remains untested. Use dependency injection, interfaces, or test-specific configuration instead.

## Noncompliant code example

```apex
public class OrderService {
    public void submitOrder(Order__c order) {
        if (Test.isRunningTest()) {                    // Noncompliant
            order.Status__c = 'Submitted';
        } else {
            ExternalService.submit(order);
            order.Status__c = 'Submitted';
        }
        update order;
    }
}
```

## Compliant solution

```apex
public class OrderService {
    private IExternalService service;

    public OrderService(IExternalService service) {
        this.service = service;
    }

    public void submitOrder(Order__c order) {
        service.submit(order);
        order.Status__c = 'Submitted';
        update order;
    }
}
```
