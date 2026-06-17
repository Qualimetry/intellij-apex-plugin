# Class source files must not exceed the maximum line count

`qa-complexity-class-line-limit` &middot; Complexity &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags class source files that exceed the configured maximum line count. Large classes typically accumulate too many responsibilities and become difficult to understand, test, and maintain. Split them into smaller, focused classes. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class GodService { // Noncompliant — exceeds line limit
    // hundreds of methods and fields...
    public void method1() { /* ... */ }
    public void method2() { /* ... */ }
    // ... 500+ more lines
}
```

## Compliant solution

```apex
public class AccountService {
    public void createAccount(Account a) { insert a; }
    public void updateAccount(Account a) { update a; }
}

public class ContactService {
    public void createContact(Contact c) { insert c; }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| maxLines | Maximum number of lines allowed in a class source file | `500` |
