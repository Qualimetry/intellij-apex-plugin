# Trailing return statements at method end are unnecessary

`qa-design-unnecessary-return` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A return statement at the very end of a void method is unnecessary because the method will return automatically. Removing it reduces noise and makes it clear the method simply falls through to completion.

## Noncompliant code example

```apex
public class EventLogger {
    public void logEvent(String eventName) {
        System.debug('Event: ' + eventName);
        return; // Noncompliant - unnecessary return at end of void method
    }

    public void clearCache() {
        Cache.Org.remove('key');
        return; // Noncompliant - unnecessary return at end of void method
    }
}
```

## Compliant solution

```apex
public class EventLogger {
    public void logEvent(String eventName) {
        System.debug('Event: ' + eventName);
    }

    public void clearCache() {
        Cache.Org.remove('key');
    }
}
```
