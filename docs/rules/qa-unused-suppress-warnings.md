# Review usage of @SuppressWarnings

`qa-unused-suppress-warnings` &middot; Unused Code &middot; Code Smell &middot; severity INFO &middot; enabled in the recommended profile

@SuppressWarnings annotations silence compiler or analyzer warnings without addressing the root cause. While sometimes necessary for framework constraints, excessive use hides real issues and prevents code quality improvements. Review each suppression to determine whether the warning can be resolved instead of suppressed.

## Noncompliant code example

```apex
public class DataLoader {
    @SuppressWarnings('PMD.AvoidDeeplyNestedIfStmts')      // Noncompliant — review needed
    public void load() {
        if (a) {
            if (b) {
                if (c) {
                    process();
                }
            }
        }
    }
}
```

## Compliant solution

```apex
public class DataLoader {
    public void load() {
        if (!a || !b || !c) return;
        process();
    }
}
```
