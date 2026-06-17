# Resolve TODO and FIXME comments before release

`qa-convention-no-todo-fixme` &middot; Convention &middot; Code Smell &middot; severity INFO &middot; optional

This rule flags TODO, FIXME, and HACK comments in source code. These markers indicate deferred work that should be tracked in an issue tracker rather than left as comments. Resolve the underlying issue before releasing the code. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class PaymentProcessor {
    public void processPayment(Payment__c p) {
        // TODO: add retry logic // Noncompliant
        // FIXME: handle null amounts // Noncompliant
        p.Status__c = 'Processed';
        update p;
    }
}
```

## Compliant solution

```apex
public class PaymentProcessor {
    public void processPayment(Payment__c p) {
        if (p.Amount__c == null) {
            throw new IllegalArgumentException('Amount cannot be null');
        }
        p.Status__c = 'Processed';
        update p;
    }
}
```
