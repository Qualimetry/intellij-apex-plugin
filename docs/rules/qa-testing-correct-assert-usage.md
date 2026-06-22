# Assert methods must be called with correct argument types

`qa-testing-correct-assert-usage` &middot; Testing &middot; Code Smell &middot; severity CRITICAL &middot; enabled in the recommended profile

Calling assert methods with incorrect argument types — such as using Assert.areEqual for boolean conditions or passing the actual value where the expected value should go — produces misleading failure messages and makes test intent unclear. Use the appropriate assertion method for the comparison type: isTrue/isFalse for booleans, areEqual for value comparisons.

## Noncompliant code example

```apex
@IsTest
private class OrderTest {
    @IsTest
    static void testOrderStatus() {
        Order__c order = createOrder();
        Assert.areEqual(true, order.IsActive__c);         // Noncompliant — use isTrue
        Assert.areEqual(order.Status__c, 'Open');         // Noncompliant — reversed args
        Assert.isTrue(order.Amount__c == 100);            // Noncompliant — use areEqual
    }
}
```

## Compliant solution

```apex
@IsTest
private class OrderTest {
    @IsTest
    static void testOrderStatus() {
        Order__c order = createOrder();
        Assert.isTrue(order.IsActive__c, 'Order should be active');
        Assert.areEqual('Open', order.Status__c, 'Status should be Open');
        Assert.areEqual(100, order.Amount__c, 'Amount should be 100');
    }
}
```
