# Class names should not exceed maximum length

`qa-convention-class-name-max-length` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Excessively long class names reduce readability and create awkward line breaks in declarations, method signatures, and import statements. Long names often signal that the class has too many responsibilities and should be decomposed. Keep class names concise while still conveying the class purpose.

## Noncompliant code example

```apex
// Noncompliant - class name exceeds 64 characters
public class ThisIsAnExtremelyLongClassNameThatExceedsTheMaximumAllowedLengthLimit {
    public void run() {
        doWork();
    }

    private void doWork() { }
}
```

## Compliant solution

```apex
public class OrderProcessingService {
    public void run() {
        doWork();
    }

    private void doWork() { }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| maxLength | Maximum number of characters allowed in a class name | `64` |
