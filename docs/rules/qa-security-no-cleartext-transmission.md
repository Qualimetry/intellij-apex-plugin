# Sensitive data must not be transmitted in cleartext

`qa-security-no-cleartext-transmission` &middot; Security &middot; Security Hotspot &middot; severity CRITICAL &middot; enabled in the recommended profile

Transmitting sensitive data such as passwords, tokens, or personal information over an unencrypted HTTP connection allows attackers to intercept the data via man-in-the-middle attacks. All HTTP endpoints must use HTTPS, and sensitive payloads should be transmitted through Named Credentials or other secure channels that enforce TLS.

## Noncompliant code example

```apex
public class AuthService {
    public static HttpResponse authenticate(String user, String pass) {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('http://api.example.com/auth');    // Noncompliant — HTTP
        req.setMethod('POST');
        req.setBody('username=' + user + '&password=' + pass);
        return new Http().send(req);
    }
}
```

## Compliant solution

```apex
public class AuthService {
    public static HttpResponse authenticate(String user, String pass) {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('callout:AuthService/auth');
        req.setMethod('POST');
        req.setBody(JSON.serialize(new Map<String, String>{
            'username' => user, 'password' => pass
        }));
        return new Http().send(req);
    }
}
```
