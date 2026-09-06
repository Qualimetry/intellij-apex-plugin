# Apex classes should use a recent Salesforce API version

`qa-salesforce-outdated-api-version` &middot; Salesforce &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Apex classes that reference an outdated Salesforce API version miss platform improvements, bug fixes, and new features. Older API versions may also be retired by Salesforce, causing deployments to fail. Keep the API version current (within the last three major releases) to ensure compatibility and access to the latest platform capabilities.

## Noncompliant code example

```apex
// In the .cls-meta.xml:
// <apiVersion>42.0</apiVersion>

public class LegacyService {         // Noncompliant — API version 42 is outdated
    public void process() {
        // Cannot use newer Salesforce features
    }
}
```

## Compliant solution

```apex
// In the .cls-meta.xml:
// <apiVersion>62.0</apiVersion>

public class ModernService {
    public void process() {
        // Full access to current platform features
    }
}
```
