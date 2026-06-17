# Do not use the String constructor for string creation

`qa-design-no-string-constructor` &middot; Design &middot; Code Smell &middot; severity CRITICAL &middot; enabled in the recommended profile

Using the new String() constructor to create a string is unnecessary because Apex strings are immutable and can be assigned directly. The constructor call wastes memory by creating a duplicate object and obscures the simple assignment intent.

## Noncompliant code example

```apex
public class StringUtils {
    public String copy(String original) {
        String result = new String(original); // Noncompliant - unnecessary constructor
        return result;
    }

    public String createEmpty() {
        return new String(); // Noncompliant - unnecessary constructor
    }
}
```

## Compliant solution

```apex
public class StringUtils {
    public String copy(String original) {
        String result = original;
        return result;
    }

    public String createEmpty() {
        return '';
    }
}
```
