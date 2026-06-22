# Boolean assertions should use isTrue/isFalse

`qa-testing-use-boolean-assert` &middot; Testing &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Using Assert.areEqual(true, condition) or Assert.areEqual(false, condition) is verbose and obscures intent. Use Assert.isTrue() or Assert.isFalse() for boolean assertions — they are more readable and produce clearer failure messages.

## Noncompliant code example

```apex
@IsTest
private class FeatureFlagTest {
    @IsTest
    static void testFlags() {
        Assert.areEqual(true, FeatureFlag.isEnabled('NewUI'));     // Noncompliant
        Assert.areEqual(false, FeatureFlag.isEnabled('Legacy'));   // Noncompliant
    }
}
```

## Compliant solution

```apex
@IsTest
private class FeatureFlagTest {
    @IsTest
    static void testFlags() {
        Assert.isTrue(FeatureFlag.isEnabled('NewUI'), 'NewUI should be enabled');
        Assert.isFalse(FeatureFlag.isEnabled('Legacy'), 'Legacy should be disabled');
    }
}
```
