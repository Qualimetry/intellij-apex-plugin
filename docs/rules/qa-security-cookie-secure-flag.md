# Cookies must have the secure flag enabled

`qa-security-cookie-secure-flag` &middot; Security &middot; Security Hotspot &middot; severity MAJOR &middot; enabled in the recommended profile

Cookies created without the isSecure flag set to true can be transmitted over unencrypted HTTP connections, exposing session tokens, user preferences, or other sensitive data to network-level attackers. Always set the secure flag so the browser only sends the cookie over HTTPS.

## Noncompliant code example

```apex
public class SessionController {
    public void setSessionCookie(String token) {
        Cookie sessionCookie = new Cookie(
            'session_token', token, null, 3600, false  // Noncompliant — isSecure=false
        );
        ApexPages.currentPage().setCookies(
            new List<Cookie>{ sessionCookie }
        );
    }
}
```

## Compliant solution

```apex
public class SessionController {
    public void setSessionCookie(String token) {
        Cookie sessionCookie = new Cookie(
            'session_token', token, '/', 3600, true    // isSecure=true
        );
        ApexPages.currentPage().setCookies(
            new List<Cookie>{ sessionCookie }
        );
    }
}
```
