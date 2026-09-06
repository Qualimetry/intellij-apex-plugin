# Loops without init/update expressions should use while

`qa-design-for-loop-should-be-while` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A for loop that omits the initialization or update expression is effectively a while loop. Using while in this case communicates intent more clearly, since for loops conventionally combine initialization, condition, and increment in a single header.

## Noncompliant code example

```apex
public class RecordIterator {
    public void processForward(List<Account> accounts) {
        Integer i = 0;
        for (; i < accounts.size(); i++) { // Noncompliant - no init expression
            System.debug(accounts[i].Name);
        }
    }

    public void processUntilDone(Iterator<String> iter) {
        for (String val = iter.next(); iter.hasNext();) { // Noncompliant - no update expression
            System.debug(val);
        }
    }
}
```

## Compliant solution

```apex
public class RecordIterator {
    public void processForward(List<Account> accounts) {
        Integer i = 0;
        while (i < accounts.size()) {
            System.debug(accounts[i].Name);
            i++;
        }
    }

    public void processUntilDone(Iterator<String> iter) {
        while (iter.hasNext()) {
            System.debug(iter.next());
        }
    }
}
```
