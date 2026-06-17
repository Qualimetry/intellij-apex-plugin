# Page actions should not perform trivial redirects

`qa-design-no-trivial-page-redirect` &middot; Design &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

A page action method that simply constructs a PageReference and calls setRedirect(true) adds an unnecessary server round-trip. Trivial redirects should be handled declaratively in the Visualforce page markup or replaced with direct navigation, improving performance and reducing controller complexity.

## Noncompliant code example

```apex
public class HomeController {
    public PageReference goToHome() {
        return Page.HomePage; setRedirect(true); // Noncompliant - trivial redirect
    }

    public PageReference goToDashboard() {
        return Page.Dashboard; setRedirect(true); // Noncompliant - trivial redirect
    }
}
```

## Compliant solution

```apex
public class HomeController {
    public PageReference goToHome() {
        PageReference ref = Page.HomePage;
        ref.getParameters().put('tab', 'overview');
        ref.setRedirect(true);
        return ref;
    }
}
```
