# Variables must not be assigned to themselves

`qa-error-handling-self-assignment` &middot; Error Handling &middot; Bug &middot; severity MAJOR &middot; optional

This rule detects statements where a variable is assigned to itself (e.g. x = x). Self-assignments have no effect and typically indicate a copy-paste error or a misunderstanding of the intended target variable. They should be corrected or removed. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class ContactUpdater {
    private String firstName;
    private String lastName;

    public void update(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        lastName = lastName; // Noncompliant — self-assignment
    }
}
```

## Compliant solution

```apex
public class ContactUpdater {
    private String firstName;
    private String lastName;

    public void update(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
```
