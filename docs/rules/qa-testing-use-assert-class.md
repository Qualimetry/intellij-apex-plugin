# Use Assert class methods instead of legacy System.Assert

`qa-testing-use-assert-class` &middot; Testing &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

The legacy System.Assert, System.assertEquals, and System.assertNotEquals methods are superseded by the Assert class (API v56+), which provides clearer method names (isTrue, areEqual, isNotNull) and better error messages. Migrating to the Assert class improves test readability and maintainability.

## Noncompliant code example

```apex
@IsTest
private class AccountTest {
    @IsTest
    static void testAccountName() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        Account result = [SELECT Name FROM Account WHERE Id = :acc.Id];
        System.assertEquals('Test', result.Name);          // Noncompliant
        System.assert(result.Id != null, 'Id set');        // Noncompliant
        System.assertNotEquals(null, result.Name);         // Noncompliant
    }
}
```

## Compliant solution

```apex
@IsTest
private class AccountTest {
    @IsTest
    static void testAccountName() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        Account result = [SELECT Name FROM Account WHERE Id = :acc.Id];
        Assert.areEqual('Test', result.Name, 'Name should match');
        Assert.isNotNull(result.Id, 'Id should be set');
        Assert.isNotNull(result.Name, 'Name should not be null');
    }
}
```
