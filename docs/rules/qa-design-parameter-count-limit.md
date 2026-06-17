# Methods must not accept too many parameters

`qa-design-parameter-count-limit` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags methods that accept more parameters than the configured threshold. Too many parameters make call sites error-prone and hard to read. Group related parameters into an object or use a builder pattern. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class OrderService {
    public void createOrder(String name, Decimal amount, String status,
                           Date orderDate, String notes, Id ownerId) { // Noncompliant
        Order o = new Order();
        o.Name = name;
        o.Amount = amount;
    }
}
```

## Compliant solution

```apex
public class OrderService {
    public void createOrder(OrderRequest request) {
        Order o = new Order();
        o.Name = request.name;
        o.Amount = request.amount;
    }
}
```
