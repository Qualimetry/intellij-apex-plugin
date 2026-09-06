# Apex classes must not reference retired API versions

`qa-salesforce-retired-api-version` &middot; Salesforce &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Salesforce periodically retires old API versions. Classes referencing a retired API version will fail to compile or deploy, and may exhibit unpredictable runtime behavior. Retired versions no longer receive security patches or bug fixes. Update the API version immediately to a currently supported release.

## Noncompliant code example

```apex
// In the .cls-meta.xml:
// <apiVersion>18.0</apiVersion>

public class RetiredApiClass {       // Noncompliant — API version 18 is retired
    public void process() { }
}
```

## Compliant solution

```apex
// In the .cls-meta.xml:
// <apiVersion>62.0</apiVersion>

public class CurrentApiClass {
    public void process() { }
}
```
