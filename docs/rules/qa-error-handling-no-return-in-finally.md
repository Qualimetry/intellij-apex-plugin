# Finally blocks should not contain return statements

`qa-error-handling-no-return-in-finally` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A return statement inside a finally block silently discards any exception that was thrown in the try or catch block. The caller receives the return value instead of the exception, hiding failures and making bugs nearly impossible to diagnose.

## Noncompliant code example

```apex
public class DataReader {
    public String readValue(String key) {
        try {
            return fetchFromCache(key);
        } catch (Exception e) {
            throw new CacheException('Read failed');
        } finally {
            return 'default'; // Noncompliant - return in finally, exception is swallowed
        }
    }

    public Integer getCount() {
        try {
            return queryCount();
        } finally {
            return 0; // Noncompliant - return in finally
        }
    }
    private String fetchFromCache(String key) { return key; }
    private Integer queryCount() { return 1; }
}
```

## Compliant solution

```apex
public class DataReader {
    public String readValue(String key) {
        try {
            return fetchFromCache(key);
        } catch (Exception e) {
            return 'default';
        }
    }

    public Integer getCount() {
        try {
            return queryCount();
        } catch (Exception e) {
            return 0;
        }
    }
    private String fetchFromCache(String key) { return key; }
    private Integer queryCount() { return 1; }
}
```
