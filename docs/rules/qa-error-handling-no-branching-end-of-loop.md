# Loop bodies should not end with a branching statement

`qa-error-handling-no-branching-end-of-loop` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Ending a loop body with a branching statement such as break, continue, or return often indicates that the loop executes at most once, making the loop construct misleading. If the loop always exits on its first iteration, it should be replaced with a conditional statement.

## Noncompliant code example

```apex
public class DataFinder {
    public Account findFirst(List<Account> accounts) {
        for (Account acc : accounts) {
            return acc; // Noncompliant - loop always exits on first iteration
        }
        return null;
    }

    public void processOnce(List<String> items) {
        for (String item : items) {
            System.debug(item);
            break; // Noncompliant - loop always exits on first iteration
        }
    }
}
```

## Compliant solution

```apex
public class DataFinder {
    public Account findFirst(List<Account> accounts) {
        if (!accounts.isEmpty()) {
            return accounts[0];
        }
        return null;
    }

    public void processOnce(List<String> items) {
        if (!items.isEmpty()) {
            System.debug(items[0]);
        }
    }
}
```
