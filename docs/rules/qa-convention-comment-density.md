# Source files must have adequate comment density

`qa-convention-comment-density` &middot; Convention &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Source files with very few comments relative to their code volume are harder to understand and maintain. A minimum comment density ensures that key decisions, complex logic, and public APIs are documented. This rule measures the ratio of comment lines to total lines and flags files that fall below the threshold.

## Noncompliant code example

```apex
public class OrderService {
    private String status;
    private Integer count;
    public void process() {
        validate();
        execute();
    }
    private void validate() { }
    private void execute() { }
    public String getStatus() { return status; }
    public Integer getCount() { return count; }
}
```

## Compliant solution

```apex
// Manages order lifecycle operations.
public class OrderService {
    private String status;
    private Integer count;

    // Validates and executes the order processing pipeline.
    public void process() {
        validate();
        execute();
    }

    private void validate() { }
    private void execute() { }
    public String getStatus() { return status; }
    public Integer getCount() { return count; }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| minCommentDensity | Minimum percentage of comment lines relative to total lines | `10` |
