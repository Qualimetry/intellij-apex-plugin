# While loop bodies must be enclosed in curly braces

`qa-convention-while-braces-required` &middot; Convention &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags while loop bodies that are not enclosed in curly braces. Omitting braces makes it easy to accidentally add a statement outside the loop body when modifying the code, leading to subtle bugs. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class QueueProcessor {
    public void drain(List<String> queue) {
        Integer i = 0;
        while (i < queue.size()) // Noncompliant — no braces
            System.debug(queue.get(i++));
    }
}
```

## Compliant solution

```apex
public class QueueProcessor {
    public void drain(List<String> queue) {
        Integer i = 0;
        while (i < queue.size()) {
            System.debug(queue.get(i));
            i++;
        }
    }
}
```
