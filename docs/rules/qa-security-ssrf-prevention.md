# Server-side HTTP requests must validate target URLs

`qa-security-ssrf-prevention` &middot; Security &middot; Security Hotspot &middot; severity CRITICAL &middot; enabled in the recommended profile

Server-Side Request Forgery (SSRF) occurs when user-controlled input is used to construct the target URL of a server-side HTTP request. An attacker can exploit this to scan internal networks, access metadata endpoints, or interact with internal services that are not exposed to the internet. Always validate target URLs against an allowlist or use Named Credentials.

## Noncompliant code example

```apex
public class IntegrationService {
    @AuraEnabled
    public static String fetchData(String endpoint) {
        HttpRequest req = new HttpRequest();
        req.setEndpoint(endpoint);              // Noncompliant — user-controlled URL
        req.setMethod('GET');
        HttpResponse res = new Http().send(req);
        return res.getBody();
    }
}
```

## Compliant solution

```apex
public class IntegrationService {
    private static final Set<String> ALLOWED_HOSTS = new Set<String>{
        'https://api.trusted-partner.com'
    };

    @AuraEnabled
    public static String fetchData(String endpoint) {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('callout:TrustedPartner' + endpoint);
        req.setMethod('GET');
        HttpResponse res = new Http().send(req);
        return res.getBody();
    }
}
```
