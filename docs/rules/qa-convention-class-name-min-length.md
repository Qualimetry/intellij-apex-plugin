# Class names must meet minimum length requirement

`qa-convention-class-name-min-length` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Very short class names (fewer than 3 characters) reduce readability because they convey no information about the class purpose. Single or two-letter class names force readers to inspect the implementation to understand what the class does, slowing down navigation and code reviews.

## Noncompliant code example

```apex
public class Ab { // Noncompliant - class name too short (fewer than 3 characters)
    public void run() {
        doWork();
    }

    private void doWork() { }
}

public class X { // Noncompliant - class name too short
    public void process() { }
}
```

## Compliant solution

```apex
public class AccountBatch {
    public void run() {
        doWork();
    }

    private void doWork() { }
}

public class Executor {
    public void process() { }
}
```
