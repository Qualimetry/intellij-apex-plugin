# Opening brace placement must be consistent

`qa-convention-left-brace-position` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Mixing opening-brace placement styles (end-of-line vs. next-line) within the same file creates visual inconsistency. When some blocks use K&R style and others use Allman style, the code looks disorganized and reviewers waste time debating formatting instead of logic. Pick one style and apply it consistently.

## Noncompliant code example

```apex
public class Example { // end-of-line style
    public void run()
    { // Noncompliant - mixed with next-line style
        doWork();
    }

    public void process() { // end-of-line style
        validate();
    }
}
```

## Compliant solution

```apex
public class Example {
    public void run() {
        doWork();
    }

    public void process() {
        validate();
    }
}
```
