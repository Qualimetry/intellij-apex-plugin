# The final modifier is unnecessary on methods of final classes

`qa-design-unnecessary-final-modifier` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Marking a method as final in a class that is already final is redundant because a final class cannot be extended, so no method can ever be overridden. The extra modifier adds visual noise without providing any additional safety.

## Noncompliant code example

```apex
public final class MathUtils {
    public final Integer add(Integer a, Integer b) { // Noncompliant - class is already final
        return a + b;
    }

    public final Integer multiply(Integer a, Integer b) { // Noncompliant - class is already final
        return a * b;
    }
}
```

## Compliant solution

```apex
public final class MathUtils {
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    public Integer multiply(Integer a, Integer b) {
        return a * b;
    }
}
```
