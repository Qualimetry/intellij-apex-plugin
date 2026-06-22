# Test methods should not have excessive assertion count

`qa-testing-max-asserts-per-test` &middot; Testing &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Test methods with too many assertions are testing multiple behaviors at once, making failures hard to diagnose and tests brittle. When one assertion fails, subsequent assertions do not execute, hiding additional issues. Split large test methods into focused tests that each verify a single behavior.

## Noncompliant code example

```apex
@IsTest
private class OrderTest {
    @IsTest
    static void testEverything() {                 // Noncompliant — too many asserts
        Order__c o = createOrder();
        Assert.isNotNull(o.Id, 'created');
        Assert.areEqual('Draft', o.Status__c, 'status');
        Assert.areEqual(100, o.Amount__c, 'amount');
        Assert.isTrue(o.IsActive__c, 'active');
        Assert.isNotNull(o.CreatedDate, 'date');
        Assert.areEqual(UserInfo.getUserId(), o.OwnerId, 'owner');
        // ... 10+ more assertions
    }
}
```

## Compliant solution

```apex
@IsTest
private class OrderTest {
    @IsTest
    static void testOrderCreation() {
        Order__c o = createOrder();
        Assert.isNotNull(o.Id, 'Order should be created');
        Assert.areEqual('Draft', o.Status__c, 'Default status should be Draft');
    }

    @IsTest
    static void testOrderOwnership() {
        Order__c o = createOrder();
        Assert.areEqual(UserInfo.getUserId(), o.OwnerId, 'Current user should own order');
    }
}
```
