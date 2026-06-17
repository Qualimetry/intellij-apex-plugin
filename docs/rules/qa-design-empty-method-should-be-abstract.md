# Empty methods in abstract classes should be declared abstract

`qa-design-empty-method-should-be-abstract` &middot; Design &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

An empty method in an abstract class suggests that the method is intended to be overridden by subclasses. Declaring it as abstract makes this intent explicit and forces subclasses to provide an implementation, eliminating the risk of accidentally inheriting a no-op method.

## Noncompliant code example

```apex
public abstract class EventHandler {
    public virtual void onBefore() { } // Noncompliant - empty body, should be abstract
    public virtual void onAfter() { } // Noncompliant - empty body, should be abstract

    public void dispatch(String eventType) {
        onBefore();
        processEvent(eventType);
        onAfter();
    }

    private void processEvent(String eventType) {
        System.debug(eventType);
    }
}
```

## Compliant solution

```apex
public abstract class EventHandler {
    public abstract void onBefore();
    public abstract void onAfter();

    public void dispatch(String eventType) {
        onBefore();
        processEvent(eventType);
        onAfter();
    }

    private void processEvent(String eventType) {
        System.debug(eventType);
    }
}
```
