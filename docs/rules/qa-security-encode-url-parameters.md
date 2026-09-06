# URL parameters must be encoded and sanitized

`qa-security-encode-url-parameters` &middot; Security &middot; Security Hotspot &middot; severity MAJOR &middot; enabled in the recommended profile

URL parameters constructed from user input without proper encoding can break URL parsing, enable parameter injection, or facilitate open redirect and XSS attacks. Special characters in parameter values must be encoded using EncodingUtil.urlEncode() to ensure they are safely transmitted as part of the URL.

## Noncompliant code example

```apex
public class RedirectController {
    public PageReference redirectToDetail() {
        String recordName = ApexPages.currentPage()
            .getParameters().get('name');
        return new PageReference('/detail?name=' + recordName);    // Noncompliant
    }

    public String buildUrl(String query) {
        return '/search?q=' + query;                               // Noncompliant
    }
}
```

## Compliant solution

```apex
public class RedirectController {
    public PageReference redirectToDetail() {
        String recordName = ApexPages.currentPage()
            .getParameters().get('name');
        String encoded = EncodingUtil.urlEncode(recordName, 'UTF-8');
        return new PageReference('/detail?name=' + encoded);
    }

    public String buildUrl(String query) {
        return '/search?q=' + EncodingUtil.urlEncode(query, 'UTF-8');
    }
}
```
