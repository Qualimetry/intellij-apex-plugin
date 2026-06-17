# Initializer blocks must not be empty

`qa-error-handling-empty-initializer` &middot; Error Handling &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

An empty instance initializer block { } serves no purpose. It runs before the constructor but performs no initialization, adding confusion about the class lifecycle without contributing any behavior.

## Noncompliant code example

```apex
public class DataService {
    private String name;

    { // Noncompliant - empty initializer block
    }

    public DataService() {
        name = 'default';
    }

    public String getName() {
        return name;
    }
}
```

## Compliant solution

```apex
public class DataService {
    private String name;

    public DataService() {
        name = 'default';
    }

    public String getName() {
        return name;
    }
}
```
