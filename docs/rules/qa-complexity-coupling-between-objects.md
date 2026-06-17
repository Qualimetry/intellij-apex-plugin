# Limit coupling between classes

`qa-complexity-coupling-between-objects` &middot; Complexity &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A class that depends on too many other classes (high coupling) is difficult to understand, test in isolation, and maintain. Changes to any dependency can ripple through the highly coupled class. Reduce coupling by extracting responsibilities into focused classes and using dependency injection or facade patterns.

## Noncompliant code example

```apex
public class OrderOrchestrator {
    public void processOrder(Id orderId) {
        AccountService accountSvc = new AccountService();       // Dependency 1
        ContactService contactSvc = new ContactService();       // Dependency 2
        ProductService productSvc = new ProductService();       // Dependency 3
        ShippingService shippingSvc = new ShippingService();    // Dependency 4
        PaymentService paymentSvc = new PaymentService();       // Dependency 5
        TaxService taxSvc = new TaxService();                   // Dependency 6
        NotificationService notifSvc = new NotificationService(); // Dependency 7
        // ... exceeds coupling threshold
    }
}
```

## Compliant solution

```apex
public class OrderOrchestrator {
    private final IOrderPipeline pipeline;

    public OrderOrchestrator(IOrderPipeline pipeline) {
        this.pipeline = pipeline;
    }

    public void processOrder(Id orderId) {
        pipeline.execute(orderId);
    }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| threshold | Maximum number of referenced types per class | `20` |
