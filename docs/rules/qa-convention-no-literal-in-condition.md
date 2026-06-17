# Conditional statements should not use literal values

`qa-convention-no-literal-in-condition` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Using magic numbers or hardcoded string literals directly inside conditional expressions makes the code harder to understand and maintain. A reader must guess what 10 or 'Active' represents. Extracting these values into named constants documents their purpose and ensures changes are made in a single place.

## Noncompliant code example

```apex
public class OrderProcessor {
    public void process(List<Order> orders) {
        String status = getStatus();
        if (status == 'Active') { // Noncompliant - string literal in condition
            activate();
        }

        Integer count = orders.size();
        if (count > 10) { // Noncompliant - magic number in condition
            splitBatch();
        }
    }
}
```

## Compliant solution

```apex
public class OrderProcessor {
    private static final String STATUS_ACTIVE = 'Active';
    private static final Integer BATCH_THRESHOLD = 10;

    public void process(List<Order> orders) {
        String status = getStatus();
        if (status == STATUS_ACTIVE) {
            activate();
        }

        Integer count = orders.size();
        if (count > BATCH_THRESHOLD) {
            splitBatch();
        }
    }
}
```
