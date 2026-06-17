# Type NCSS must not exceed threshold

`qa-complexity-ncss-type` &middot; Complexity &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Classes with a high total Non-Commenting Source Statement (NCSS) count have accumulated too much code, indicating the class has too many responsibilities. Large classes are hard to navigate, test, and maintain. Split them into focused, cohesive classes that each serve a single purpose.

## Noncompliant code example

```apex
public class MegaController {                      // Noncompliant — NCSS exceeds threshold
    // 500+ lines of mixed account, contact, and opportunity logic
    public void createAccount() { /* 30 lines */ }
    public void createContact() { /* 30 lines */ }
    public void createOpportunity() { /* 30 lines */ }
    // ... many more methods
}
```

## Compliant solution

```apex
public class AccountController {
    public void createAccount() { /* focused logic */ }
}

public class ContactController {
    public void createContact() { /* focused logic */ }
}
```
