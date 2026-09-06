# Catch blocks should not merely rethrow the same exception type

`qa-error-handling-no-rethrow` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A catch block that only rethrows the caught exception adds no value. The exception would have propagated naturally without the try-catch. This pattern clutters the code and may mislead readers into thinking the catch block provides meaningful error handling.

## Noncompliant code example

```apex
public class OrderService {
    public void createOrder(Order__c order) {
        try {
            insert order;
        } catch (DmlException e) { // Noncompliant - merely rethrows
            throw e;
        }
    }

    public void deleteOrder(Id orderId) {
        try {
            delete [SELECT Id FROM Order__c WHERE Id = :orderId];
        } catch (Exception e) { // Noncompliant - merely rethrows
            throw e;
        }
    }
}
```

## Compliant solution

```apex
public class OrderService {
    public void createOrder(Order__c order) {
        insert order;
    }

    public void deleteOrder(Id orderId) {
        delete [SELECT Id FROM Order__c WHERE Id = :orderId];
    }
}
```
