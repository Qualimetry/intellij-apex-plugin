# HTTP Referer must not be used for security decisions

`qa-security-no-referer-header` &middot; Security &middot; Security Hotspot &middot; severity CRITICAL &middot; enabled in the recommended profile

The HTTP Referer header is controlled by the client and can be omitted entirely, spoofed, or stripped by proxies and privacy settings. Using it for authorization decisions, CSRF protection, or access control creates a trivially exploitable authentication bypass. Use server-side session tokens or platform-provided CSRF mechanisms instead.

## Noncompliant code example

```apex
public class SecureController {
    public PageReference verifyOrigin() {
        String referer = ApexPages.currentPage()
            .getHeaders().get('Referer');               // Noncompliant
        if (referer == null || !referer.contains('myapp.salesforce.com')) {
            throw new SecurityException('Invalid origin');
        }
        return processRequest();
    }
}
```

## Compliant solution

```apex
public class SecureController {
    public PageReference verifyOrigin() {
        String csrfToken = ApexPages.currentPage()
            .getParameters().get('csrf');
        if (!CsrfTokenService.isValid(csrfToken)) {
            throw new SecurityException('Invalid CSRF token');
        }
        return processRequest();
    }
}
```
