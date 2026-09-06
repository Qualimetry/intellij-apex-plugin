# Assertion parameters must follow expected-then-actual order

`qa-testing-assert-parameter-order` &middot; Testing &middot; Bug &middot; severity MAJOR &middot; optional

This rule flags assertion calls where the expected and actual values appear to be in the wrong order. Swapped parameters produce confusing failure messages that report the actual value as expected and vice versa, making test failures harder to diagnose. Follow the convention of expected first, then actual. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
@IsTest
private class AccountTest {
    @IsTest
    static void testAccountName() {
        Account a = new Account(Name = 'Acme');
        insert a;
        Account result = [SELECT Name FROM Account WHERE Id = :a.Id];
        System.assertEquals(result.Name, 'Acme', 'Name mismatch'); // Noncompliant — actual first
    }
}
```

## Compliant solution

```apex
@IsTest
private class AccountTest {
    @IsTest
    static void testAccountName() {
        Account a = new Account(Name = 'Acme');
        insert a;
        Account result = [SELECT Name FROM Account WHERE Id = :a.Id];
        System.assertEquals('Acme', result.Name, 'Name mismatch');
    }
}
```
