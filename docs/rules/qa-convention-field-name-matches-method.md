# Field names must not match method names

`qa-convention-field-name-matches-method` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

When a field and a method share the same name, the code becomes confusing because the reader must rely on context (parentheses, assignment) to determine whether the identifier refers to the field or the method invocation. This ambiguity increases the risk of maintenance errors and makes auto-complete suggestions less useful.

## Noncompliant code example

```apex
public class OrderService {
    private String status; // Noncompliant - same name as method 'status'
    private Integer count; // Noncompliant - same name as method 'count'

    public String status() {
        return status;
    }

    public Integer count() {
        return count;
    }
}
```

## Compliant solution

```apex
public class OrderService {
    private String currentStatus;
    private Integer itemCount;

    public String getStatus() {
        return currentStatus;
    }

    public Integer getCount() {
        return itemCount;
    }
}
```
