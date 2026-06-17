# Method parameters should not be reassigned

`qa-design-no-parameter-reassignment` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Reassigning a method parameter obscures the original value passed by the caller, making the method harder to debug and reason about. When the parameter is later referenced, it is unclear whether the original or modified value is in use. Introduce a local variable instead to preserve the original parameter value.

## Noncompliant code example

```apex
public class StringProcessor {
    public String normalize(String input) {
        input = input.trim(); // Noncompliant - parameter reassigned
        input = input.toLowerCase(); // Noncompliant - parameter reassigned again
        return input;
    }

    public Integer clampValue(Integer value, Integer maxVal) {
        if (value > maxVal) {
            value = maxVal; // Noncompliant - parameter reassigned
        }
        return value;
    }
}
```

## Compliant solution

```apex
public class StringProcessor {
    public String normalize(String input) {
        String normalized = input.trim();
        normalized = normalized.toLowerCase();
        return normalized;
    }

    public Integer clampValue(Integer value, Integer maxVal) {
        Integer result = value;
        if (result > maxVal) {
            result = maxVal;
        }
        return result;
    }
}
```
