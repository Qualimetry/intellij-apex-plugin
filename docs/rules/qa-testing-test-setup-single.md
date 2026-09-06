# Test classes should have at most one @TestSetup method

`qa-testing-test-setup-single` &middot; Testing &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

This rule flags test classes that contain more than one @TestSetup method. Salesforce only supports a single @TestSetup method per test class. If multiple methods are annotated, the platform silently ignores all but one, leading to incomplete or unpredictable test data setup and hard-to-diagnose test failures.

## Noncompliant code example

```apex
@IsTest
private class AccountServiceTest {

    @TestSetup
    static void setupAccounts() {
        insert new Account(Name = 'Test Account');
    }

    @TestSetup // Noncompliant — second @TestSetup
    static void setupContacts() {
        insert new Contact(LastName = 'Test Contact');
    }

    @IsTest
    static void testAccountCreation() {
        List<Account> accounts = [SELECT Id FROM Account];
        System.assertEquals(1, accounts.size(), 'Expected one test account');
    }
}
```

## Compliant solution

```apex
@IsTest
private class AccountServiceTest {

    @TestSetup
    static void setupTestData() {
        insert new Account(Name = 'Test Account');
        insert new Contact(LastName = 'Test Contact');
    }

    @IsTest
    static void testAccountCreation() {
        List<Account> accounts = [SELECT Id FROM Account];
        System.assertEquals(1, accounts.size(), 'Expected one test account');
    }
}
```
