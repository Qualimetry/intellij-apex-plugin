# Test classes must use the @IsTest annotation

`qa-testing-istest-annotation` &middot; Testing &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags test classes that use the legacy testMethod keyword instead of the @IsTest annotation. The @IsTest annotation is the modern, recommended approach and supports additional options such as SeeAllData and isParallel. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
private class AccountTest {
    static testMethod void testCreate() { // Noncompliant — uses testMethod keyword
        Account a = new Account(Name = 'Test');
        insert a;
        System.assertNotEquals(null, a.Id);
    }
}
```

## Compliant solution

```apex
@IsTest
private class AccountTest {
    @IsTest
    static void testCreate() {
        Account a = new Account(Name = 'Test');
        insert a;
        System.assertNotEquals(null, a.Id, 'Account should have an Id after insert');
    }
}
```
