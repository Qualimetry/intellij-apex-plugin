# Abstract classes must declare at least one abstract or virtual member

`qa-design-abstract-needs-abstract-method` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags abstract classes that do not declare at least one abstract or virtual method. An abstract class without abstract members provides no behavioral contract for subclasses and suggests a design that should either be a concrete class or should define override points. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public abstract class BaseProcessor { // Noncompliant
    public void process() {
        System.debug(LoggingLevel.INFO, 'Processing');
    }

    public void log(String message) {
        System.debug(LoggingLevel.INFO, message);
    }
}
```

## Compliant solution

```apex
public abstract class BaseProcessor {
    public abstract void process();

    public void log(String message) {
        System.debug(LoggingLevel.INFO, message);
    }
}
```
