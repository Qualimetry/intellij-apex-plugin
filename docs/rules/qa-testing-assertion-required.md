# Test methods must contain at least one assertion

`qa-testing-assertion-required` &middot; Testing &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags test methods that do not contain any assertion statements. A test without assertions only verifies that code does not throw an exception, providing weak verification. Include meaningful assertions to validate expected outcomes. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
@IsTest
private class AccountServiceTest {
    @IsTest
    static void testCreateAccount() { // Noncompliant — no assertion
        Account a = new Account(Name = 'Test');
        insert a;
    }
}
```

## Compliant solution

```apex
@IsTest
private class AccountServiceTest {
    @IsTest
    static void testCreateAccount() {
        Account a = new Account(Name = 'Test');
        insert a;
        Account result = [SELECT Id, Name FROM Account WHERE Id = :a.Id];
        System.assertEquals('Test', result.Name, 'Account name should match');
    }
}
```
