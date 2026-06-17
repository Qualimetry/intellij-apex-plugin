# Operations on immutable values have no effect

`qa-unused-operation-on-immutable` &middot; Unused Code &middot; Code Smell &middot; severity CRITICAL &middot; enabled in the recommended profile

Operations on immutable values — such as calling String.replace(), String.trim(), or String.toLowerCase() without assigning the result — have no effect because these methods return a new value instead of modifying the original. The result must be captured in a variable or used directly. Ignoring the return value is always a bug.

## Noncompliant code example

```apex
public class TextFormatter {
    public String format(String input) {
        input.trim();                              // Noncompliant — result discarded
        input.toLowerCase();                       // Noncompliant — result discarded
        input.replace('old', 'new');               // Noncompliant — result discarded
        return input;
    }
}
```

## Compliant solution

```apex
public class TextFormatter {
    public String format(String input) {
        input = input.trim();
        input = input.toLowerCase();
        input = input.replace('old', 'new');
        return input;
    }
}
```
