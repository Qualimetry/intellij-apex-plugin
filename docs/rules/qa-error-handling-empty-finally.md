# Finally blocks must contain at least one statement

`qa-error-handling-empty-finally` &middot; Error Handling &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

An empty finally block serves no purpose and suggests incomplete implementation. If cleanup is not needed, remove the finally clause entirely. If cleanup was intended, add the missing resource-release logic.

## Noncompliant code example

```apex
public class ResourceManager {
    public void loadData() {
        try {
            fetchFromApi();
        } catch (Exception e) {
            System.debug(e.getMessage());
        } finally { // Noncompliant - empty finally block
        }
    }

    public void processRecords() {
        try {
            insert new Account(Name = 'Test');
        } finally { // Noncompliant - empty finally block
        }
    }
    private void fetchFromApi() { }
}
```

## Compliant solution

```apex
public class ResourceManager {
    public void loadData() {
        try {
            fetchFromApi();
        } catch (Exception e) {
            System.debug(e.getMessage());
        }
    }

    public void processRecords() {
        insert new Account(Name = 'Test');
    }
    private void fetchFromApi() { }
}
```
