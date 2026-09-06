# Use Assert.isTrue for boolean conditions

`qa-testing-istrue-over-areequal` &middot; Testing &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Using Assert.areEqual(true, condition) for boolean checks is verbose and obscures intent. Use Assert.isTrue(condition) instead — it is more readable, more concise, and produces a clearer failure message.

## Noncompliant code example

```apex
@IsTest
private class PermissionTest {
    @IsTest
    static void testAccess() {
        Assert.areEqual(true, hasAccess());               // Noncompliant
        Assert.areEqual(false, isBlocked());              // Noncompliant
    }
}
```

## Compliant solution

```apex
@IsTest
private class PermissionTest {
    @IsTest
    static void testAccess() {
        Assert.isTrue(hasAccess(), 'User should have access');
        Assert.isFalse(isBlocked(), 'User should not be blocked');
    }
}
```
