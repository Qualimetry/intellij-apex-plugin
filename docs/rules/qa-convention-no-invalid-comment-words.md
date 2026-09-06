# Comments must not contain prohibited words or phrases

`qa-convention-no-invalid-comment-words` &middot; Convention &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Comments containing words like TODO, FIXME, HACK, or XXX indicate unfinished or questionable code that should not remain in production. These markers are useful during development but must be resolved before merging. Leaving them signals technical debt and can accumulate to the point where they are ignored entirely.

## Noncompliant code example

```apex
public class OrderService {
    // TODO: implement validation logic // Noncompliant
    public void validate() { }

    // FIXME: handle edge cases // Noncompliant
    public void process() { }

    public void submit() {
        send();
    }
}
```

## Compliant solution

```apex
public class OrderService {
    public void validate() {
        validateInput();
        validateRules();
    }

    public void process() {
        handleEdgeCases();
        executeLogic();
    }

    public void submit() {
        send();
    }
}
```
