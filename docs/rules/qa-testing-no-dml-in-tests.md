# Test methods should use test data factories instead of DML

`qa-testing-no-dml-in-tests` &middot; Testing &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Test methods that perform DML directly (insert, update, delete) create tight coupling between tests and data setup, make tests harder to maintain, and risk governor limit issues when the same setup is duplicated across many test methods. Use @TestSetup methods or test data factory classes to centralize record creation.

## Noncompliant code example

```apex
@IsTest
private class AccountServiceTest {
    @IsTest
    static void testProcess() {
        Account acc = new Account(Name = 'Test');
        insert acc;                                     // Noncompliant — DML in test method
        Contact con = new Contact(LastName = 'Test', AccountId = acc.Id);
        insert con;                                     // Noncompliant
        AccountService.process(acc.Id);
        Assert.isNotNull(acc.Id);
    }
}
```

## Compliant solution

```apex
@IsTest
private class AccountServiceTest {
    @TestSetup
    static void setup() {
        Account acc = TestDataFactory.createAccount();
        TestDataFactory.createContact(acc.Id);
    }

    @IsTest
    static void testProcess() {
        Account acc = [SELECT Id FROM Account LIMIT 1];
        AccountService.process(acc.Id);
        Assert.isNotNull(acc.Id);
    }
}
```
