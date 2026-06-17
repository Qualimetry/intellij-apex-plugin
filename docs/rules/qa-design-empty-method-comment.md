# Empty method bodies must include a justifying comment

`qa-design-empty-method-comment` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags empty method bodies that lack a comment explaining why they are intentionally empty. An empty method may indicate unfinished code or an intentional no-op; a comment distinguishes between the two. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class EventHandler {
    public void onBeforeInsert(List<Account> accounts) {
        // Noncompliant — empty without explanation
    }
    public void onAfterUpdate(List<Account> accounts) {
    }
}
```

## Compliant solution

```apex
public class EventHandler {
    public void onBeforeInsert(List<Account> accounts) {
        // intentionally empty — no before-insert logic needed
    }
    public void onAfterUpdate(List<Account> accounts) {
        // TODO: implement after-update logic in next sprint
    }
}
```
