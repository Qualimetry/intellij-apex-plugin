# Test class names must contain 'Test'

`qa-testing-class-name-includes-test` &middot; Testing &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Test class names that do not contain the word "Test" make it difficult to identify test classes in the codebase, search for related tests, and enforce coverage requirements. Following a consistent naming convention (e.g., MyClassTest or TestMyClass) improves discoverability and tooling support.

## Noncompliant code example

```apex
@IsTest
private class AccountVerifier {                // Noncompliant — no "Test" in name
    @IsTest
    static void testCreate() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        Assert.isNotNull(acc.Id);
    }
}
```

## Compliant solution

```apex
@IsTest
private class AccountServiceTest {
    @IsTest
    static void testCreate() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        Assert.isNotNull(acc.Id);
    }
}
```
