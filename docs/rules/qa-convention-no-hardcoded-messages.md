# Error messages must not be hardcoded

`qa-convention-no-hardcoded-messages` &middot; Convention &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags hardcoded error and user-facing messages in Apex code. Hardcoded strings cannot be translated, are difficult to maintain consistently, and violate separation of concerns. Use Custom Labels or constants for messages displayed to users. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class OrderController {
    public void submitOrder(Order o) {
        if (o.Amount <= 0) {
            throw new ApplicationException('Order amount must be positive'); // Noncompliant
        }
        if (o.Status == 'Cancelled') {
            ApexPages.addMessage(new ApexPages.Message(
                ApexPages.Severity.ERROR, 'Cannot submit cancelled order')); // Noncompliant
        }
    }
}
```

## Compliant solution

```apex
public class OrderController {
    public void submitOrder(Order o) {
        if (o.Amount <= 0) {
            throw new ApplicationException(Label.Order_Amount_Positive);
        }
        if (o.Status == 'Cancelled') {
            ApexPages.addMessage(new ApexPages.Message(
                ApexPages.Severity.ERROR, Label.Cannot_Submit_Cancelled));
        }
    }
}
```
