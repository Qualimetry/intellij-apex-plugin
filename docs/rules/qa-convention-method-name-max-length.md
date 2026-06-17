# Method names should not exceed maximum length

`qa-convention-method-name-max-length` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Excessively long method names hurt readability and create awkward line breaks at call sites. A method name longer than 64 characters usually indicates that the name is trying to describe too much behavior, suggesting the method should be split into smaller, well-named methods. Aim for concise names that clearly convey intent.

## Noncompliant code example

```apex
public class AccountService {
    // Noncompliant - method name exceeds 64 characters
    public void thisIsAnExtremelyLongMethodNameThatExceedsTheMaximumAllowedLength() {
        doWork();
    }

    private void doWork() { }

    public void process() {
        validate();
    }

    private void validate() { }
}
```

## Compliant solution

```apex
public class AccountService {
    public void processAccounts() {
        doWork();
    }

    private void doWork() { }

    public void process() {
        validate();
    }

    private void validate() { }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| maxLength | Maximum number of characters allowed in a method name | `64` |
