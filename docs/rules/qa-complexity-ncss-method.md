# Method NCSS must not exceed threshold

`qa-complexity-ncss-method` &middot; Complexity &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Methods with a high Non-Commenting Source Statement (NCSS) count are too long and likely violate the Single Responsibility Principle. Long methods are difficult to understand, test, and debug. Extract logical blocks into well-named helper methods that each do one thing.

## Noncompliant code example

```apex
public class InvoiceProcessor {
    public void processInvoice(Invoice__c inv) {   // Noncompliant — NCSS exceeds threshold
        // validate
        if (inv.Amount__c == null) throw new IllegalArgumentException('Amount');
        if (inv.Status__c == null) throw new IllegalArgumentException('Status');
        // calculate tax
        Decimal tax = inv.Amount__c * 0.08;
        inv.Tax__c = tax;
        // apply discount
        if (inv.DiscountCode__c != null) {
            Discount__c d = [SELECT Percent__c FROM Discount__c WHERE Code__c = :inv.DiscountCode__c];
            inv.Amount__c -= inv.Amount__c * d.Percent__c / 100;
        }
        // ... 40+ more lines
        update inv;
    }
}
```

## Compliant solution

```apex
public class InvoiceProcessor {
    public void processInvoice(Invoice__c inv) {
        validate(inv);
        calculateTax(inv);
        applyDiscount(inv);
        update inv;
    }

    private void validate(Invoice__c inv) { /* ... */ }
    private void calculateTax(Invoice__c inv) { /* ... */ }
    private void applyDiscount(Invoice__c inv) { /* ... */ }
}
```
