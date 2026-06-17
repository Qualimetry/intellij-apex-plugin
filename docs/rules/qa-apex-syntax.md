# Apex syntax check

`qa-apex-syntax` &middot; Apex &middot; Code Smell &middot; severity CRITICAL &middot; enabled in the recommended profile

Apex classes and triggers must follow valid Apex syntax. Syntax errors prevent compilation and deployment to Salesforce, and may cause runtime failures in production.

This rule verifies the basic structure of .cls and .trigger files to catch issues early in the development workflow.

- The file contains unmatched braces, parentheses, or brackets.

- There are missing semicolons or unclosed string literals.

- Class or trigger declarations are malformed.

- Use an IDE with Apex language support for real-time syntax validation.

- Run the Apex compiler or deploy to a scratch org to verify syntax before committing.

## Noncompliant code example

```apex
public class AccountService {
    public void process() {
        System.debug('missing closing brace')
    // missing }
}
```

## Compliant solution

```apex
public class AccountService {
    public void process() {
        System.debug('all braces matched');
    }
}
```
