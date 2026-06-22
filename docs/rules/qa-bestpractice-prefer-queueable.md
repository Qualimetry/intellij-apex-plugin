# Prefer Queueable over @Future

`qa-bestpractice-prefer-queueable` &middot; Best Practice &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

This rule detects methods annotated with @Future and recommends replacing them with the Queueable interface. Queueable Apex provides superior flexibility: it supports chaining, accepts complex parameter types, and returns a job ID for monitoring. Using @Future limits your asynchronous design and makes it harder to evolve the code over time.

## Noncompliant code example

```apex
public class OrderProcessor {

    @Future // Noncompliant
    public static void processOrderAsync(Set<Id> orderIds) {
        List<Order> orders = [SELECT Id, Status FROM Order WHERE Id IN :orderIds];
        for (Order o : orders) {
            o.Status = 'Processed';
        }
        update orders;
    }

    @future(callout=true) // Noncompliant
    public static void sendConfirmation(String endpoint) {
        Http h = new Http();
        HttpRequest req = new HttpRequest();
        req.setEndpoint(endpoint);
        req.setMethod('POST');
        h.send(req);
    }
}
```

## Compliant solution

```apex
public class OrderProcessorJob implements Queueable {

    private Set<Id> orderIds;

    public OrderProcessorJob(Set<Id> orderIds) {
        this.orderIds = orderIds;
    }

    public void execute(QueueableContext ctx) {
        List<Order> orders = [SELECT Id, Status FROM Order WHERE Id IN :orderIds];
        for (Order o : orders) {
            o.Status = 'Processed';
        }
        update orders;
    }
}
// Usage: System.enqueueJob(new OrderProcessorJob(orderIds));
```
