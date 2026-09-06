# URL parameters must be escaped to prevent XSS

`qa-security-xss-url-param` &middot; Security &middot; Security Hotspot &middot; severity CRITICAL &middot; enabled in the recommended profile

This rule detects URL parameters being added to a PageReference without proper encoding. Unescaped user input in URL parameters can be exploited for cross-site scripting (XSS) attacks when the URL is rendered in the browser. Always use EncodingUtil.urlEncode() to sanitize values before inserting them into URL parameters.

## Noncompliant code example

```apex
public class RedirectController {
    public PageReference redirect() {
        String userInput = ApexPages.currentPage().getParameters().get('name');
        PageReference p = Page.TargetPage;
        p.getParameters().put('name', userInput); // Noncompliant — unescaped
        p.getParameters().put('source', 'search'); // Noncompliant — unescaped user flow
        p.setRedirect(true);
        return p;
    }
}
```

## Compliant solution

```apex
public class RedirectController {
    public PageReference redirect() {
        String userInput = ApexPages.currentPage().getParameters().get('name');
        PageReference p = Page.TargetPage;
        p.getParameters().put('name', EncodingUtil.urlEncode(userInput, 'UTF-8'));
        p.getParameters().put('source', EncodingUtil.urlEncode('search', 'UTF-8'));
        p.setRedirect(true);
        return p;
    }
}
```
