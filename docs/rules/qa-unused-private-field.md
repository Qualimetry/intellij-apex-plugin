# Remove private fields that are never read

`qa-unused-private-field` &middot; Unused Code &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Private fields that are declared but never read anywhere in the class are dead code. They consume memory, clutter the class definition, and confuse maintainers who may assume they serve a purpose. Remove unused private fields or, if they were intended for future use, implement the functionality that reads them.

## Noncompliant code example

```apex
public class OrderProcessor {
    private String legacyCode;                     // Noncompliant — never read
    private Integer retryCount;                    // Noncompliant — never read
    private Boolean isProcessed;

    public void process(Order__c order) {
        isProcessed = true;
        order.Status__c = 'Processed';
        update order;
    }

    public Boolean getIsProcessed() {
        return isProcessed;
    }
}
```

## Compliant solution

```apex
public class OrderProcessor {
    private Boolean isProcessed;

    public void process(Order__c order) {
        isProcessed = true;
        order.Status__c = 'Processed';
        update order;
    }

    public Boolean getIsProcessed() {
        return isProcessed;
    }
}
```
