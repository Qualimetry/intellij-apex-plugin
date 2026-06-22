# Final fields with constant values should be static

`qa-convention-final-field-should-be-static` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

A final instance field initialized with a constant value holds the same data in every instance of the class, wasting memory. Declaring such fields static makes the constant shared across all instances and communicates that the value belongs to the type rather than to individual objects.

## Noncompliant code example

```apex
public class Config {
    private final Integer MAX_RETRIES = 3; // Noncompliant - should be static
    private final String ENDPOINT = 'https://api.example.com'; // Noncompliant - should be static

    public void process() {
        for (Integer i = 0; i < MAX_RETRIES; i++) {
            callout(ENDPOINT);
        }
    }

    private void callout(String url) { }
}
```

## Compliant solution

```apex
public class Config {
    private static final Integer MAX_RETRIES = 3;
    private static final String ENDPOINT = 'https://api.example.com';

    public void process() {
        for (Integer i = 0; i < MAX_RETRIES; i++) {
            callout(ENDPOINT);
        }
    }

    private void callout(String url) { }
}
```
