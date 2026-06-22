# Remove unused access modifiers

`qa-unused-modifier` &middot; Unused Code &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Access modifiers that have no effect in context — such as private on members of a private inner class, or redundant visibility keywords — add noise to the code without changing behavior. Remove unnecessary modifiers to keep declarations clean and focused on meaningful access control decisions.

## Noncompliant code example

```apex
public class Outer {
    private class Inner {
        private String data;          // Noncompliant — private is redundant in private class
        private void process() { }    // Noncompliant
    }
}
```

## Compliant solution

```apex
public class Outer {
    private class Inner {
        String data;
        void process() { }
    }
}
```
