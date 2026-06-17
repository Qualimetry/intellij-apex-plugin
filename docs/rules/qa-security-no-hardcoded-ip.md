# IP addresses must not be hardcoded

`qa-security-no-hardcoded-ip` &middot; Security &middot; Security Hotspot &middot; severity MAJOR &middot; optional

This rule flags hardcoded IP addresses in Apex source code. Hardcoded IPs create security risks by exposing infrastructure details, prevent environment-specific configuration, and break when network topology changes. Use Named Credentials or Custom Metadata for endpoint configuration. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class ApiClient {
    public String fetchData() {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('http://192.168.1.100/api/data'); // Noncompliant
        req.setMethod('GET');
        Http http = new Http();
        HttpResponse res = http.send(req);
        return res.getBody();
    }
}
```

## Compliant solution

```apex
public class ApiClient {
    public String fetchData() {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('callout:MyNamedCredential/api/data');
        req.setMethod('GET');
        Http http = new Http();
        HttpResponse res = http.send(req);
        return res.getBody();
    }
}
```
