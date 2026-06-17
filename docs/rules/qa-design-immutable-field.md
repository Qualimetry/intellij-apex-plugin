# Fields that are never reassigned should be declared final

`qa-design-immutable-field` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A field that is assigned once at declaration or in the constructor and never reassigned is effectively immutable. Declaring it final communicates this intent and prevents accidental modification, making the class safer and easier to reason about.

## Noncompliant code example

```apex
public class OrderService {
    private String apiEndpoint = 'https://api.example.com'; // Noncompliant - never reassigned
    private Integer timeout = 30000; // Noncompliant - never reassigned

    public HttpResponse callApi(String path) {
        HttpRequest req = new HttpRequest();
        req.setEndpoint(apiEndpoint + path);
        req.setTimeout(timeout);
        return new Http().send(req);
    }
}
```

## Compliant solution

```apex
public class OrderService {
    private final String apiEndpoint = 'https://api.example.com';
    private final Integer timeout = 30000;

    public HttpResponse callApi(String path) {
        HttpRequest req = new HttpRequest();
        req.setEndpoint(apiEndpoint + path);
        req.setTimeout(timeout);
        return new Http().send(req);
    }
}
```
