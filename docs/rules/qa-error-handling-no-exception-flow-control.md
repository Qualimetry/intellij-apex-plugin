# Exceptions should not be used for regular control flow

`qa-error-handling-no-exception-flow-control` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Using exceptions for regular control flow (e.g., throwing to exit a loop or switch between code paths) is expensive, hard to follow, and obscures genuine error conditions. Exceptions should be reserved for unexpected or exceptional situations. Use conditional logic for normal branching.

## Noncompliant code example

```apex
public class ItemFinder {
    public Account findAccount(String name) {
        try {
            for (Account acc : [SELECT Id, Name FROM Account]) {
                if (acc.Name == name) {
                    throw new FoundException(acc); // Noncompliant - exception as flow control
                }
            }
        } catch (FoundException e) {
            return e.getAccount();
        }
        return null;
    }
}
```

## Compliant solution

```apex
public class ItemFinder {
    public Account findAccount(String name) {
        for (Account acc : [SELECT Id, Name FROM Account]) {
            if (acc.Name == name) {
                return acc;
            }
        }
        return null;
    }
}
```
