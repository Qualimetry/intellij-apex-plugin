# Salesforce page URLs must use relative paths

`qa-salesforce-relative-url` &middot; Salesforce &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags hardcoded absolute URLs pointing to Salesforce pages (e.g., https://my.salesforce.com/...). Absolute URLs break when the org's domain changes or when deploying to sandboxes. Use relative paths or URL.getOrgDomainUrl() instead. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class PageRedirector {
    public PageReference goToAccount(Id accountId) {
        // Noncompliant — absolute URL to a Salesforce page
        return new PageReference('https://na1.salesforce.com/' + accountId);
    }
}
```

## Compliant solution

```apex
public class PageRedirector {
    public PageReference goToAccount(Id accountId) {
        return new PageReference('/' + accountId);
    }
}
```
