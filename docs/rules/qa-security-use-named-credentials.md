# HTTP callouts must use Named Credentials

`qa-security-use-named-credentials` &middot; Security &middot; Security Hotspot &middot; severity CRITICAL &middot; enabled in the recommended profile

Hardcoding HTTP endpoint URLs and authentication credentials in Apex callout code creates multiple risks: credentials are exposed in source code and version control, endpoints cannot be changed per environment without code deployment, and secrets cannot be rotated without a release. Use Salesforce Named Credentials to manage endpoints and authentication declaratively.

## Noncompliant code example

```apex
public class ExternalApiService {
    public static HttpResponse callApi() {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('https://api.partner.com/v2/data');          // Noncompliant
        req.setHeader('Authorization', 'Bearer sk_live_abc123xyz'); // Noncompliant
        req.setMethod('GET');
        return new Http().send(req);
    }
}
```

## Compliant solution

```apex
public class ExternalApiService {
    public static HttpResponse callApi() {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('callout:PartnerAPI/v2/data');
        req.setMethod('GET');
        return new Http().send(req);
    }
}
```
