# Platform event publishing inside loops hits governor limits

`qa-salesforce-event-publish-loop` &middot; Salesforce &middot; Code Smell &middot; severity CRITICAL &middot; enabled in the recommended profile

This rule detects platform event publish calls (EventBus.publish()) inside loop bodies. Each publish call inside a loop consumes a separate DML statement against the governor limit, and Salesforce limits the number of events published per transaction. Publishing events in bulk outside the loop avoids hitting governor limits and improves transaction efficiency.

## Noncompliant code example

```apex
public class OrderEventPublisher {
    public void publishOrderEvents(List<Order> orders) {
        for (Order o : orders) {
            Order_Event__e evt = new Order_Event__e();
            evt.OrderId__c = o.Id;
            evt.Status__c = o.Status;
            EventBus.publish(evt); // Noncompliant — publish inside loop
        }
    }
}
```

## Compliant solution

```apex
public class OrderEventPublisher {
    public void publishOrderEvents(List<Order> orders) {
        List<Order_Event__e> events = new List<Order_Event__e>();
        for (Order o : orders) {
            Order_Event__e evt = new Order_Event__e();
            evt.OrderId__c = o.Id;
            evt.Status__c = o.Status;
            events.add(evt);
        }
        EventBus.publish(events);
    }
}
```
