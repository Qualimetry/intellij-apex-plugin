# Method names must meet minimum length requirement

`qa-convention-method-name-min-length` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Single-character method names provide no indication of what the method does. Meaningful method names are essential for readable code because they allow developers to understand a class's behavior by scanning its method signatures without reading every implementation.

## Noncompliant code example

```apex
public class Calculator {
    public Integer a() { // Noncompliant - method name too short
        return 1;
    }

    public void b() { // Noncompliant - method name too short
        System.debug('done');
    }

    public void process() {
        Integer result = a();
        b();
    }
}
```

## Compliant solution

```apex
public class Calculator {
    public Integer computeTotal() {
        return 1;
    }

    public void printResult() {
        System.debug('done');
    }

    public void process() {
        Integer result = computeTotal();
        printResult();
    }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| minLength | Minimum number of characters allowed in a method name | `2` |
