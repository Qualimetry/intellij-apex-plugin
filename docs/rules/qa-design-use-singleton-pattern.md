# Classes with only static methods should use the singleton pattern

`qa-design-use-singleton-pattern` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A class that contains only static methods and has no instance state is effectively a utility class. Using the singleton pattern (private constructor plus a static instance accessor) or simply making the class non-instantiable prevents callers from creating meaningless instances.

## Noncompliant code example

```apex
public class DateUtils {
    public static Date parseDate(String input) {
        return Date.valueOf(input);
    }

    public static String formatDate(Date d) {
        return String.valueOf(d);
    }
}
```

## Compliant solution

```apex
public class DateUtils {
    private DateUtils() { }

    public static Date parseDate(String input) {
        return Date.valueOf(input);
    }

    public static String formatDate(Date d) {
        return String.valueOf(d);
    }
}
```
