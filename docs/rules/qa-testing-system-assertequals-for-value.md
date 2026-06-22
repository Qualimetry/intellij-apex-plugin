# Use System.assertEquals for value equality

`qa-testing-system-assertequals-for-value` &middot; Testing &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Using System.assert(a == b) for value comparison instead of System.assertEquals(a, b) produces uninformative failure messages — the output only says "Assertion Failed" without revealing the actual and expected values. Use System.assertEquals() for value comparisons to get actionable failure output.

## Noncompliant code example

```apex
@IsTest
private class PriceTest {
    @IsTest
    static void testDiscount() {
        Decimal price = PriceCalculator.applyDiscount(100, 10);
        System.assert(price == 90, 'Discount applied');        // Noncompliant
    }
}
```

## Compliant solution

```apex
@IsTest
private class PriceTest {
    @IsTest
    static void testDiscount() {
        Decimal price = PriceCalculator.applyDiscount(100, 10);
        System.assertEquals(90, price, 'Price after 10% discount');
    }
}
```
