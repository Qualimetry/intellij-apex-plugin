# Variable names should not exceed maximum length

`qa-convention-variable-name-max-length` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Variable and parameter names that exceed a reasonable length make code harder to read and create unnecessary line wrapping. While descriptive names are important, excessively long names suggest the variable is trying to describe too much context. Aim for names that are clear but concise.

## Noncompliant code example

```apex
public class OrderProcessor {
    public void process() {
        // Noncompliant - variable name exceeds 64 characters
        String thisIsAnExtremelyLongVariableNameThatExceedsTheMaximumAllowedLength = 'test';
        System.debug(thisIsAnExtremelyLongVariableNameThatExceedsTheMaximumAllowedLength);
    }

    public void validate() {
        Boolean valid = true;
    }
}
```

## Compliant solution

```apex
public class OrderProcessor {
    public void process() {
        String orderLabel = 'test';
        System.debug(orderLabel);
    }

    public void validate() {
        Boolean valid = true;
    }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| maxLength | Maximum number of characters allowed in a variable name | `64` |
