# Closing brace placement must be consistent

`qa-convention-right-brace-position` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Placing a closing brace on the same line as other statements makes it easy to overlook the block boundary. Putting } on its own line with matching indentation clearly delineates where a block ends, improving readability and consistency across the codebase.

## Noncompliant code example

```apex
public class Example {
    public void run() {
        if (true) {
            doWork(); } // Noncompliant - closing brace not on its own line
    }

    public void process() {
        while (false) {
            validate();
        }
    }
}
```

## Compliant solution

```apex
public class Example {
    public void run() {
        if (true) {
            doWork();
        }
    }

    public void process() {
        while (false) {
            validate();
        }
    }
}
```
