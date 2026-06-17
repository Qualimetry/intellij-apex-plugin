# Test methods must reside in @IsTest classes

`qa-testing-methods-in-test-class` &middot; Testing &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Test methods (annotated with @IsTest or using the testMethod keyword) must reside inside a class that is also annotated with @IsTest. Placing test methods in production classes mixes concerns, inflates class size, and can cause unexpected behavior when the test framework scans for test methods.

## Noncompliant code example

```apex
public class AccountService {
    public void process() { /* production logic */ }

    @IsTest                                        // Noncompliant — test in non-test class
    static void testProcess() {
        new AccountService().process();
        Assert.isTrue(true);
    }
}
```

## Compliant solution

```apex
// AccountService.cls
public class AccountService {
    public void process() { /* production logic */ }
}

// AccountServiceTest.cls
@IsTest
private class AccountServiceTest {
    @IsTest
    static void testProcess() {
        new AccountService().process();
        Assert.isTrue(true, 'Process completed');
    }
}
```
