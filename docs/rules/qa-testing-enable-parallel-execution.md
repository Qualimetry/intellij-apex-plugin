# Test classes should enable @IsTest(isParallel=true)

`qa-testing-enable-parallel-execution` &middot; Testing &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Test classes that do not opt into parallel execution with @IsTest(isParallel=true) run sequentially by default, significantly increasing total test execution time. Parallel test execution reduces feedback cycles and CI pipeline duration. Enable parallel execution unless the test class requires exclusive access to shared data.

## Noncompliant code example

```apex
@IsTest                                         // Noncompliant — no isParallel
private class AccountServiceTest {
    @IsTest
    static void testProcess() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        AccountService.process(acc.Id);
        Assert.isNotNull(acc.Id, 'Account created');
    }
}
```

## Compliant solution

```apex
@IsTest(isParallel=true)
private class AccountServiceTest {
    @IsTest
    static void testProcess() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        AccountService.process(acc.Id);
        Assert.isNotNull(acc.Id, 'Account created');
    }
}
```
