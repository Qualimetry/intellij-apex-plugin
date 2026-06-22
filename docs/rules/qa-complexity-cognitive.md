# Method cognitive complexity must not exceed threshold

`qa-complexity-cognitive` &middot; Complexity &middot; Code Smell &middot; severity CRITICAL &middot; optional

This rule measures the cognitive complexity of methods and flags those exceeding the configured threshold. Cognitive complexity accounts for nesting depth and control flow breaks, reflecting how difficult code is for a human to understand. Refactor complex methods by extracting helper methods and simplifying control flow. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class OrderProcessor {
    public void process(Order o) { // Noncompliant — high cognitive complexity
        if (o != null) {
            if (o.Status == 'New') {
                for (OrderItem item : o.OrderItems) {
                    if (item.Quantity > 0) {
                        if (item.UnitPrice != null) {
                            item.TotalPrice = item.Quantity * item.UnitPrice;
                        }
                    }
                }
            }
        }
    }
}
```

## Compliant solution

```apex
public class OrderProcessor {
    public void process(Order o) {
        if (o == null || o.Status != 'New') { return; }
        calculateItemTotals(o.OrderItems);
    }

    private void calculateItemTotals(List<OrderItem> items) {
        for (OrderItem item : items) {
            if (item.Quantity > 0 && item.UnitPrice != null) {
                item.TotalPrice = item.Quantity * item.UnitPrice;
            }
        }
    }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| threshold | Maximum cognitive complexity allowed for a method | `15` |
