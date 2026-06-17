# Redirects must validate target URLs

`qa-security-validate-redirect-url` &middot; Security &middot; Security Hotspot &middot; severity MAJOR &middot; enabled in the recommended profile

Redirecting users to a URL taken directly from request parameters enables open redirect attacks. An attacker can craft a link that appears to come from a trusted domain but redirects the victim to a phishing site or malicious page. Always validate redirect target URLs against an allowlist of permitted destinations.

## Noncompliant code example

```apex
public class LoginController {
    public PageReference handleLogin() {
        String returnUrl = ApexPages.currentPage()
            .getParameters().get('returnUrl');
        if (authenticate()) {
            return new PageReference(returnUrl);           // Noncompliant — unvalidated redirect
        }
        return null;
    }
}
```

## Compliant solution

```apex
public class LoginController {
    private static final Set<String> ALLOWED_PATHS = new Set<String>{
        '/home', '/dashboard', '/accounts'
    };

    public PageReference handleLogin() {
        String returnUrl = ApexPages.currentPage()
            .getParameters().get('returnUrl');
        if (authenticate()) {
            if (returnUrl != null && ALLOWED_PATHS.contains(returnUrl)) {
                return new PageReference(returnUrl);
            }
            return new PageReference('/home');
        }
        return null;
    }
}
```
