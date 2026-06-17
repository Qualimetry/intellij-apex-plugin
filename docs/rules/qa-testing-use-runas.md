# Tests verifying permissions should use System.runAs

`qa-testing-use-runas` &middot; Testing &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags tests that verify permission-dependent behavior without wrapping the tested code in System.runAs(). Without runAs, tests execute with the running user's full permissions, masking sharing or profile issues that real users would encounter. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
@IsTest
private class AccountAccessTest {
    @IsTest
    static void testStandardUserAccess() { // Noncompliant — no System.runAs
        Account a = new Account(Name = 'Test');
        insert a;
        System.assertNotEquals(null, a.Id);
    }
}
```

## Compliant solution

```apex
@IsTest
private class AccountAccessTest {
    @IsTest
    static void testStandardUserAccess() {
        User stdUser = TestDataFactory.createStandardUser();
        System.runAs(stdUser) {
            Account a = new Account(Name = 'Test');
            insert a;
            System.assertNotEquals(null, a.Id, 'Standard user should be able to create accounts');
        }
    }
}
```
