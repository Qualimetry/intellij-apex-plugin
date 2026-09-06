# All unit test methods must pass

`qa-testing-no-failing-tests` &middot; Testing &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Failing unit tests indicate broken functionality that has not been addressed. Tests that fail consistently erode team confidence in the test suite, lead to ignoring test results, and block deployments. All test methods must pass before code is considered ready for deployment.

## Noncompliant code example

```apex
@IsTest
private class PaymentServiceTest {
    @IsTest
    static void testPayment() {
        Payment__c payment = new Payment__c(Amount__c = 100);
        insert payment;
        PaymentService.process(payment.Id);
        Payment__c result = [SELECT Status__c FROM Payment__c WHERE Id = :payment.Id];
        Assert.areEqual('Completed', result.Status__c, 'Payment should complete');  // Fails
    }
}
```

## Compliant solution

```apex
@IsTest
private class PaymentServiceTest {
    @IsTest
    static void testPayment() {
        Payment__c payment = new Payment__c(Amount__c = 100);
        insert payment;
        Test.startTest();
        PaymentService.process(payment.Id);
        Test.stopTest();
        Payment__c result = [SELECT Status__c FROM Payment__c WHERE Id = :payment.Id];
        Assert.areEqual('Completed', result.Status__c, 'Payment should complete');
    }
}
```
