# While loop bodies must not be empty

`qa-error-handling-empty-while` &middot; Error Handling &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

An empty while loop body is almost always a bug. The loop condition is repeatedly evaluated but no work is performed. If the intent is to busy-wait, this wastes CPU. If the body was accidentally left empty, add the missing logic.

## Noncompliant code example

```apex
public class QueueProcessor {
    public void drain(List<String> queue) {
        while (!queue.isEmpty()) { // Noncompliant - empty while body
        }
    }

    public void waitForReady(Boolean ready) {
        while (!ready) { // Noncompliant - empty while body, infinite loop
        }
    }
}
```

## Compliant solution

```apex
public class QueueProcessor {
    public void drain(List<String> queue) {
        while (!queue.isEmpty()) {
            queue.remove(0);
        }
    }

    public void waitForReady() {
        Integer retries = 0;
        while (retries < 10) {
            retries++;
        }
    }
}
```
