# Abstract classes must define at least one method

`qa-design-abstract-class-no-methods` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

An abstract class that declares no methods provides no behavioral contract for its subclasses. Without at least one abstract or concrete method, the class adds a layer of indirection with no benefit, violating the purpose of abstraction. Subclasses inherit nothing useful, and the type hierarchy becomes misleading.

## Noncompliant code example

```apex
public abstract class BaseProcessor { // Noncompliant - no methods defined
    public String name;
    public Integer priority;
}

public abstract class EmptyHandler { // Noncompliant - no methods defined
    protected Boolean isActive;
    protected String category;
}
```

## Compliant solution

```apex
public abstract class BaseProcessor {
    public String name;
    public Integer priority;
    public abstract void execute();
}

public abstract class EmptyHandler {
    protected Boolean isActive;
    protected String category;
    public virtual void handle() { }
}
```
