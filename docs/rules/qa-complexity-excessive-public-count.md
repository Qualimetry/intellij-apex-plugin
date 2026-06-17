# Classes should not expose too many public members

`qa-complexity-excessive-public-count` &middot; Complexity &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A class that exposes too many public members (fields, methods, properties) has an oversized public API that is difficult for consumers to navigate and for maintainers to evolve safely. Each public member is a commitment to backward compatibility. Reduce the public surface by making implementation details private and grouping related functionality into separate classes.

## Noncompliant code example

```apex
public class MegaService {                         // Noncompliant — too many public members
    public String name;
    public Integer count;
    public void processA() { }
    public void processB() { }
    public void processC() { }
    public void processD() { }
    // ... 20+ public members
}
```

## Compliant solution

```apex
public class OrderService {
    private String name;
    private Integer count;

    public void processOrder(Order__c order) { }
    public Order__c getOrder(Id orderId) { return null; }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| threshold | Maximum number of public members per class | `15` |
