# Empty constructors should include a justification comment

`qa-design-uncommented-empty-constructor` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

An empty constructor with no comment leaves readers guessing whether it was left blank intentionally (e.g., to enforce a specific access level) or is an oversight. Adding a brief comment explaining why the constructor is empty makes the design decision explicit.

## Noncompliant code example

```apex
public class CacheManager {
    public CacheManager() { } // Noncompliant - empty constructor without comment

    public void clear() {
        Cache.Org.remove('key');
    }
}

public class UtilityService {
    private UtilityService() { } // Noncompliant - empty constructor without comment

    public static void doWork() { }
}
```

## Compliant solution

```apex
public class CacheManager {
    // Default constructor: initialization handled by platform cache
    public CacheManager() { }

    public void clear() {
        Cache.Org.remove('key');
    }
}

public class UtilityService {
    // Private to prevent instantiation of utility class
    private UtilityService() { }

    public static void doWork() { }
}
```
