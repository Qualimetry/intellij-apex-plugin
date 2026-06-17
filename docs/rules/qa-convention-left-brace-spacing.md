# Opening brace spacing must be consistent

`qa-convention-left-brace-spacing` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

A missing space before the opening brace (e.g. if(cond){ instead of if (cond) {) compresses visual information and makes the code harder to scan. Consistent spacing before { aligns with the most common Apex style guides and improves readability during code reviews.

## Noncompliant code example

```apex
public class Example {
    public void run(){ // Noncompliant - no space before {
        doWork();
    }

    public void process(){ // Noncompliant - no space before {
        validate();
    }

    public void submit() {
        send();
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

    public void submit() {
        send();
    }
}
```
