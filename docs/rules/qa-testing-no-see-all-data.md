# Test classes and methods must not use seeAllData=true

`qa-testing-no-see-all-data` &middot; Testing &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags test classes and methods annotated with @IsTest(seeAllData=true). Tests using seeAllData=true access real org data, making tests fragile and environment-dependent. Use @TestSetup methods or test data factories to create isolated test data. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
@IsTest(seeAllData=true) // Noncompliant
private class AccountTest {
    @IsTest
    static void testAccountQuery() {
        List<Account> accounts = [SELECT Id FROM Account LIMIT 1];
        System.assertNotEquals(null, accounts, 'Expected accounts');
    }
}
```

## Compliant solution

```apex
@IsTest
private class AccountTest {
    @TestSetup
    static void createTestData() {
        insert new Account(Name = 'Test Account');
    }

    @IsTest
    static void testAccountQuery() {
        List<Account> accounts = [SELECT Id FROM Account];
        System.assertEquals(1, accounts.size(), 'Expected one test account');
    }
}
```
