# Test methods should bracket code with Test.startTest/stopTest

`qa-testing-start-stop-test` &middot; Testing &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags test methods that do not use Test.startTest() and Test.stopTest(). These calls reset governor limits for the code under test, ensuring tests verify production-like behavior rather than running with combined test-setup limits. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
@IsTest
private class BatchTest {
    @IsTest
    static void testBatch() { // Noncompliant — no startTest/stopTest
        insert new Account(Name = 'Test');
        Database.executeBatch(new AccountBatch());
    }
}
```

## Compliant solution

```apex
@IsTest
private class BatchTest {
    @IsTest
    static void testBatch() {
        insert new Account(Name = 'Test');
        Test.startTest();
        Database.executeBatch(new AccountBatch());
        Test.stopTest();
        System.assertEquals(1, [SELECT COUNT() FROM Account], 'Expected one account');
    }
}
```
