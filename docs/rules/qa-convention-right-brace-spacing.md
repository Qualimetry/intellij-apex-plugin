# Closing brace spacing must be consistent

`qa-convention-right-brace-spacing` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Writing }else without a space between the closing brace and the keyword makes the code visually dense and harder to parse. A consistent space after } before keywords like else, catch, or finally keeps the structure clear and aligns with standard formatting conventions.

## Noncompliant code example

```apex
public class OrderService {
    public void process(Boolean active) {
        if (active) {
            doWork();
        }else { // Noncompliant - no space between } and else
            doNothing();
        }
    }

    public void validate(Boolean valid) {
        if (valid) {
            accept();
        }else { // Noncompliant - no space between } and else
            reject();
        }
    }
}
```

## Compliant solution

```apex
public class OrderService {
    public void process(Boolean active) {
        if (active) {
            doWork();
        } else {
            doNothing();
        }
    }

    public void validate(Boolean valid) {
        if (valid) {
            accept();
        } else {
            reject();
        }
    }
}
```
