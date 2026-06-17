# Class fields must not be public

`qa-security-no-public-class-fields` &middot; Security &middot; Security Hotspot &middot; severity MINOR &middot; enabled in the recommended profile

Declaring class fields with public access breaks encapsulation and allows any external code to read or modify internal state without validation. This can lead to data corruption, security bypasses, and makes the class contract impossible to enforce. Use private fields with getter/setter methods that can validate input and control access.

## Noncompliant code example

```apex
public class UserConfig {
    public String apiKey;                  // Noncompliant
    public static Integer maxRetries;      // Noncompliant
    public Boolean isAdmin;                // Noncompliant

    public void callApi() {
        HttpRequest req = new HttpRequest();
        req.setHeader('Authorization', apiKey);
    }
}
```

## Compliant solution

```apex
public class UserConfig {
    private String apiKey;
    private static Integer maxRetries;
    private Boolean isAdmin;

    public String getApiKey() { return apiKey; }
    public void setApiKey(String key) {
        if (String.isBlank(key)) throw new IllegalArgumentException('Key required');
        this.apiKey = key;
    }
}
```
