# Prefer Assert.areEqual over Assert.isTrue for equality

`qa-testing-areequal-over-istrue` &middot; Testing &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Using Assert.isTrue(a == b) for equality checks instead of Assert.areEqual(a, b) loses the benefit of the framework automatically showing both expected and actual values on failure. Prefer Assert.areEqual() for any comparison between two values.

## Noncompliant code example

```apex
@IsTest
private class OrderTest {
    @IsTest
    static void testOrderTotal() {
        Order__c order = createOrder(100, 200);
        Assert.isTrue(order.Total__c == 300);              // Noncompliant
        Assert.isTrue(order.Status__c == 'Open');          // Noncompliant
    }
}
```

## Compliant solution

```apex
@IsTest
private class OrderTest {
    @IsTest
    static void testOrderTotal() {
        Order__c order = createOrder(100, 200);
        Assert.areEqual(300, order.Total__c, 'Total should be sum of line items');
        Assert.areEqual('Open', order.Status__c, 'New order should be Open');
    }
}
```
