# Comments should not exceed maximum length

`qa-convention-comment-max-length` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Excessively long comment lines are difficult to read, especially in side-by-side diffs or on narrow displays. Keeping comments within a reasonable length (default 120 characters) encourages concise writing and ensures readability across different editor configurations. Long explanations should be split across multiple lines.

## Noncompliant code example

```apex
public class OrderService {
    // This is an extremely long comment that exceeds the maximum allowed line length and should be wrapped across multiple lines for readability // Noncompliant
    public void process() { }

    // Another very long comment line that goes on and on with detailed explanation of every little aspect of the implementation that nobody will ever read in its entirety // Noncompliant
    public void validate() { }
}
```

## Compliant solution

```apex
public class OrderService {
    // Processes orders in the queue.
    // Validates input before execution.
    public void process() { }

    // Validates the order against business rules
    // before submission to the fulfillment system.
    public void validate() { }
}
```
