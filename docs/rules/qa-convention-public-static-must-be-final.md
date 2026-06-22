# Public static fields must be declared final

`qa-convention-public-static-must-be-final` &middot; Convention &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Public static fields that are not declared final can be modified by any code in the application, making state changes unpredictable and difficult to trace. This creates a hidden coupling between classes and can lead to concurrency issues in multi-threaded contexts such as asynchronous Apex. Declaring these fields final ensures their value cannot be accidentally overwritten.

## Noncompliant code example

```apex
public class AppConfig {
    public static Integer maxRetries = 3; // Noncompliant - public static not final
    public static String endpoint = 'https://api.example.com'; // Noncompliant - public static not final

    public void process() {
        for (Integer i = 0; i < maxRetries; i++) {
            callout(endpoint);
        }
    }

    private void callout(String url) { }
}
```

## Compliant solution

```apex
public class AppConfig {
    public static final Integer MAX_RETRIES = 3;
    public static final String ENDPOINT = 'https://api.example.com';

    public void process() {
        for (Integer i = 0; i < MAX_RETRIES; i++) {
            callout(ENDPOINT);
        }
    }

    private void callout(String url) { }
}
```
