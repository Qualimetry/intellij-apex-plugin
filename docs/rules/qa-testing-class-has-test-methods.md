# Test classes must contain at least one test method

`qa-testing-class-has-test-methods` &middot; Testing &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A test class annotated with @IsTest that contains no test methods provides no coverage and misleads developers into thinking functionality is tested. Every test class must include at least one method annotated with @IsTest or using the testMethod keyword.

## Noncompliant code example

```apex
@IsTest
private class EmptyTestClass {                 // Noncompliant — no test methods
    static Account createAccount() {
        return new Account(Name = 'Helper');
    }
}
```

## Compliant solution

```apex
@IsTest
private class AccountServiceTest {
    @IsTest
    static void testAccountCreation() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        Assert.isNotNull(acc.Id, 'Account should be created');
    }
}
```
