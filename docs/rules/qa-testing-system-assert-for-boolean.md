# Use System.Assert for simple boolean conditions

`qa-testing-system-assert-for-boolean` &middot; Testing &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Using System.assertEquals(true, condition) for boolean verification is verbose. Use System.assert(condition) for simple boolean checks — it is more concise and clearly communicates that you are verifying a boolean condition rather than comparing two values.

## Noncompliant code example

```apex
@IsTest
private class ValidatorTest {
    @IsTest
    static void testValidation() {
        System.assertEquals(true, Validator.isValid('test'));   // Noncompliant
        System.assertEquals(false, Validator.isValid(''));      // Noncompliant
    }
}
```

## Compliant solution

```apex
@IsTest
private class ValidatorTest {
    @IsTest
    static void testValidation() {
        System.assert(Validator.isValid('test'), 'Should be valid');
        System.assert(!Validator.isValid(''), 'Empty should be invalid');
    }
}
```
