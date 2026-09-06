# Session ID retrieval requires security review

`qa-security-session-id-review` &middot; Security &middot; Security Hotspot &middot; severity CRITICAL &middot; enabled in the recommended profile

Calling UserInfo.getSessionId() retrieves the current user's session token. If this value is logged, returned to the client, stored insecurely, or transmitted to external systems, it enables session hijacking — an attacker who obtains the session ID can impersonate the user. Session IDs should only be used for authenticated server-side API callouts and must never be exposed to the browser or debug logs.

## Noncompliant code example

```apex
public class SessionHelper {
    @AuraEnabled
    public static String getSession() {
        String sid = UserInfo.getSessionId();    // Noncompliant — returned to client
        return sid;
    }

    public static void logSession() {
        System.debug('Session: ' + UserInfo.getSessionId());  // Noncompliant — logged
    }
}
```

## Compliant solution

```apex
public class SessionHelper {
    public static HttpResponse callInternalApi(String path) {
        HttpRequest req = new HttpRequest();
        req.setEndpoint(URL.getOrgDomainUrl().toExternalForm() + path);
        req.setHeader('Authorization', 'Bearer ' + UserInfo.getSessionId());
        req.setMethod('GET');
        return new Http().send(req);
    }
}
```
