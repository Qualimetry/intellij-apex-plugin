# Do not embed assignments inside expressions

`qa-convention-no-assignment-in-operand` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Embedding an assignment inside a conditional expression (such as if (x = 5)) is almost always a mistake, easily confused with the equality test ==. Even when intentional, it makes the code harder to read because the reader must determine whether the author intended comparison or assignment. Separating the assignment from the condition removes this ambiguity.

## Noncompliant code example

```apex
public class OrderProcessor {
    public void processOrders(List<Order> orders) {
        Integer count;
        if (count = 0) { // Noncompliant - assignment inside if condition
            System.debug('No orders');
        }

        Integer index;
        while (index = orders.size()) { // Noncompliant - assignment inside while condition
            index--;
        }
    }
}
```

## Compliant solution

```apex
public class OrderProcessor {
    public void processOrders(List<Order> orders) {
        Integer count = 0;
        if (count == 0) {
            System.debug('No orders');
        }

        Integer index = orders.size();
        while (index > 0) {
            index--;
        }
    }
}
```
