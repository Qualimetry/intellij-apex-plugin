# Cookie usage requires security review

`qa-security-cookie-usage-review` &middot; Security &middot; Security Hotspot &middot; severity CRITICAL &middot; enabled in the recommended profile

Cookies are a common attack vector for session hijacking, cross-site scripting (XSS), and cross-site request forgery (CSRF). Every use of cookies in Apex must be reviewed to ensure the Secure flag is set (HTTPS-only transmission), the path and domain are correctly scoped, and sensitive data is not stored in cookie values. Consider whether cookies are necessary or if server-side session state is more appropriate.

## Noncompliant code example

```apex
public class PreferenceController {
    public void savePreference(String value) {
        Cookie pref = new Cookie(
            'user_pref', value, null, 86400, false);    // Noncompliant — review required
        ApexPages.currentPage().setCookies(
            new List<Cookie>{ pref }
        );
    }

    public void saveSession(String token) {
        Cookie session = new Cookie(
            'session', token, null, 3600, false);       // Noncompliant
        ApexPages.currentPage().setCookies(
            new List<Cookie>{ session }
        );
    }
}
```

## Compliant solution

```apex
public class PreferenceController {
    public void savePreference(String value) {
        Cookie pref = new Cookie(
            'user_pref', value, '/', 86400, true);
        ApexPages.currentPage().setCookies(
            new List<Cookie>{ pref }
        );
    }
}
```
