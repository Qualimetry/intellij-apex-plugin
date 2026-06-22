# NullPointerException should not be caught explicitly

`qa-error-handling-no-catch-npe` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Catching NullPointerException is almost always a sign that proper null checks are missing upstream. Handling NPE at catch time means the program has already entered an undefined state, and recovery is unreliable. Fix the root cause by validating inputs and references before they are dereferenced.

## Noncompliant code example

```apex
public class SafeProcessor {
    public void process(Account acc) {
        try {
            String name = acc.Name.toUpperCase();
        } catch (NullPointerException e) { // Noncompliant - catch NPE instead of null check
            System.debug('Account or name was null');
        }
    }

    public void load(String data) {
        try {
            Integer len = data.length();
        } catch (NullPointerException npe) { // Noncompliant
            System.debug('Data was null');
        }
    }
}
```

## Compliant solution

```apex
public class SafeProcessor {
    public void process(Account acc) {
        if (acc != null && acc.Name != null) {
            String name = acc.Name.toUpperCase();
        }
    }

    public void load(String data) {
        if (data != null) {
            Integer len = data.length();
        }
    }
}
```
