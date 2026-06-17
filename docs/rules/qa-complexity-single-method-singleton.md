# Singleton must not rely on a single entry method

`qa-complexity-single-method-singleton` &middot; Complexity &middot; Code Smell &middot; severity CRITICAL &middot; enabled in the recommended profile

A singleton class that exposes its entire functionality through a single entry method creates a facade that hides excessive complexity. The single method typically becomes a God Method with high cyclomatic complexity. Refactor the singleton to expose multiple focused public methods, or decompose the class into smaller, more cohesive components.

## Noncompliant code example

```apex
public class AppManager {
    private static AppManager instance;

    public static AppManager getInstance() {
        if (instance == null) instance = new AppManager();
        return instance;
    }

    public void run(String command) {              // Noncompliant — single entry point
        if (command == 'create') { /* 50 lines */ }
        else if (command == 'delete') { /* 50 lines */ }
        else if (command == 'update') { /* 50 lines */ }
        // ...
    }
}
```

## Compliant solution

```apex
public class AppManager {
    private static AppManager instance;

    public static AppManager getInstance() {
        if (instance == null) instance = new AppManager();
        return instance;
    }

    public void create() { /* focused logic */ }
    public void remove() { /* focused logic */ }
    public void modify() { /* focused logic */ }
}
```
