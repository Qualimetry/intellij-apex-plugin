# Exception handlers must preserve the original exception details

`qa-error-handling-preserve-exception-info` &middot; Error Handling &middot; Code Smell &middot; severity CRITICAL &middot; enabled in the recommended profile

When catching an exception and throwing a new one, the original exception must be preserved (typically by passing it as the cause). Discarding the original exception loses the root cause, stack trace, and diagnostic details, making production debugging extremely difficult.

## Noncompliant code example

```apex
public class PaymentService {
    public void processPayment(Decimal amount) {
        try {
            callPaymentGateway(amount);
        } catch (CalloutException e) {
            throw new PaymentException('Payment failed'); // Noncompliant - original exception lost
        }
    }

    public void refund(Id paymentId) {
        try {
            callRefundApi(paymentId);
        } catch (Exception e) {
            throw new ApplicationException('Refund error'); // Noncompliant - original exception lost
        }
    }
    private void callPaymentGateway(Decimal amount) { }
    private void callRefundApi(Id paymentId) { }
}
```

## Compliant solution

```apex
public class PaymentService {
    public void processPayment(Decimal amount) {
        try {
            callPaymentGateway(amount);
        } catch (CalloutException e) {
            throw new PaymentException('Payment failed', e);
        }
    }

    public void refund(Id paymentId) {
        try {
            callRefundApi(paymentId);
        } catch (Exception e) {
            throw new ApplicationException('Refund error: ' + e.getMessage());
        }
    }
    private void callPaymentGateway(Decimal amount) { }
    private void callRefundApi(Id paymentId) { }
}
```
