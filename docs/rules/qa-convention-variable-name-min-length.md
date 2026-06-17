# Variable names must meet minimum length requirement

`qa-convention-variable-name-min-length` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Single-character variable names (except conventional loop counters like i) give no indication of the variable's purpose. Short names force readers to trace assignments and usage to understand meaning, which slows down comprehension and increases the risk of confusion, especially when multiple short names appear close together.

## Noncompliant code example

```apex
public class AccountProcessor {
    public void process() {
        String x = 'Active'; // Noncompliant - variable name too short
        Integer n = 10;
        System.debug(x);

        Boolean b = true; // Noncompliant - variable name too short
        System.debug(b);
    }
}
```

## Compliant solution

```apex
public class AccountProcessor {
    public void process() {
        String status = 'Active';
        Integer count = 10;
        System.debug(status);

        Boolean isActive = true;
        System.debug(isActive);
    }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| minLength | Minimum number of characters allowed in a variable name | `2` |
