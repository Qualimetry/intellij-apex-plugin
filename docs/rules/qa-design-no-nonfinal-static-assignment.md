# Non-final static fields should not be assigned after initialization

`qa-design-no-nonfinal-static-assignment` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Reassigning a non-final static field outside of its declaration creates shared mutable state that is difficult to reason about and prone to race conditions in multi-threaded contexts. When multiple methods can modify the same static field, the class behavior becomes unpredictable and hard to test. Declare static fields as final, or use instance fields when mutability is required.

## Noncompliant code example

```apex
public class ConfigManager {
    static Integer retryCount;
    static String endpoint;

    public void initialize() {
        retryCount = 3; // Noncompliant - reassigning non-final static field
    }

    public void updateEndpoint(String url) {
        endpoint = url; // Noncompliant - reassigning non-final static field
    }
}
```

## Compliant solution

```apex
public class ConfigManager {
    static final Integer RETRY_COUNT = 3;
    static final String DEFAULT_ENDPOINT = 'https://api.example.com';

    private Integer currentRetryCount;
    private String currentEndpoint;

    public void initialize() {
        currentRetryCount = RETRY_COUNT;
        currentEndpoint = DEFAULT_ENDPOINT;
    }
}
```
