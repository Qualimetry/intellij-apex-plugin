# Null checks must appear before the variable is dereferenced

`qa-error-handling-misplaced-null-check` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A null check that appears after the variable has already been dereferenced provides no protection against NullPointerException. If the variable is null, the dereference on the earlier line will already have thrown. Move the null check before the first use.

## Noncompliant code example

```apex
public class SafeLogger {
    public void log(Account acc) {
        String name = acc.Name; // Dereferences acc
        if (acc != null) { // Noncompliant - null check after dereference
            System.debug(name);
        }
    }

    public void process(String input) {
        Integer len = input.length(); // Dereferences input
        if (input != null) { // Noncompliant - null check after dereference
            System.debug(len);
        }
    }
}
```

## Compliant solution

```apex
public class SafeLogger {
    public void log(Account acc) {
        if (acc != null) {
            String name = acc.Name;
            System.debug(name);
        }
    }

    public void process(String input) {
        if (input != null) {
            Integer len = input.length();
            System.debug(len);
        }
    }
}
```
