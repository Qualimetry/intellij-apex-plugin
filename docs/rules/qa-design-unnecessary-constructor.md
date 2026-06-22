# Constructors that only call super() are redundant

`qa-design-unnecessary-constructor` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A constructor that only calls super() without any additional initialization is redundant because the compiler generates this call automatically. Removing the constructor reduces boilerplate and keeps the class focused on meaningful code.

## Noncompliant code example

```apex
public class OrderHandler extends BaseHandler {
    public OrderHandler() { // Noncompliant - only calls super()
        super();
    }

    public void process(Order__c order) {
        System.debug(order.Name);
    }
}

public class InvoiceHandler extends BaseHandler {
    public InvoiceHandler() { // Noncompliant - empty, implicitly calls super()
    }

    public void process(Invoice__c inv) {
        System.debug(inv.Name);
    }
}
```

## Compliant solution

```apex
public class OrderHandler extends BaseHandler {
    public void process(Order__c order) {
        System.debug(order.Name);
    }
}

public class InvoiceHandler extends BaseHandler {
    public void process(Invoice__c inv) {
        System.debug(inv.Name);
    }
}
```
