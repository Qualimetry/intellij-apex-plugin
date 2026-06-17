# Use assertEquals for value comparison

`qa-testing-use-assert-equals` &middot; Testing &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Using Assert.isTrue(a == b) for value comparison instead of Assert.areEqual(a, b) produces unhelpful failure messages — it only says "expected true but was false" without showing the actual values. Use Assert.areEqual() to get clear messages that display both expected and actual values.

## Noncompliant code example

```apex
@IsTest
private class AccountTest {
    @IsTest
    static void testName() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        Account result = [SELECT Name FROM Account WHERE Id = :acc.Id];
        Assert.isTrue(result.Name == 'Test');           // Noncompliant
    }
}
```

## Compliant solution

```apex
@IsTest
private class AccountTest {
    @IsTest
    static void testName() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        Account result = [SELECT Name FROM Account WHERE Id = :acc.Id];
        Assert.areEqual('Test', result.Name, 'Account name should match');
    }
}
```
