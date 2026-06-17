# HTTP requests should set explicit timeout

`qa-salesforce-http-timeout` &middot; Salesforce &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

This rule detects HTTP callout send() calls that are not preceded by a setTimeout() configuration on the request. Without an explicit timeout, callouts default to the platform maximum (120 seconds in Apex), which can consume the entire transaction time allowance if the external service is slow or unresponsive. Setting a timeout enables graceful failure and retry logic.

## Noncompliant code example

```apex
public class ExternalApiService {
    public String fetchData(String endpoint) {
        HttpRequest req = new HttpRequest();
        req.setEndpoint(endpoint);
        req.setMethod('GET');
        req.setHeader('Content-Type', 'application/json');
        Http http = new Http();
        HttpResponse res = http.send(req); // Noncompliant — no setTimeout
        return res.getBody();
    }
}
```

## Compliant solution

```apex
public class ExternalApiService {
    public String fetchData(String endpoint) {
        HttpRequest req = new HttpRequest();
        req.setEndpoint(endpoint);
        req.setMethod('GET');
        req.setHeader('Content-Type', 'application/json');
        req.setTimeout(30000);
        Http http = new Http();
        HttpResponse res = http.send(req);
        return res.getBody();
    }
}
```
