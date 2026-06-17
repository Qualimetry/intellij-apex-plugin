# Standalone empty statements should be removed

`qa-error-handling-empty-statement` &middot; Error Handling &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

A standalone empty statement (a stray semicolon ;) outside of a loop body is almost always a mistake. It may indicate an accidentally terminated if-statement or a leftover from deleted code. Remove the empty statement to avoid confusion.

## Noncompliant code example

```apex
public class DataProcessor {
    public void process(List<Account> accounts) {
        for (Account acc : accounts) {
            acc.Name = acc.Name.trim();
        }; // Noncompliant - empty statement

        if (accounts.size() > 0) {
            System.debug('Done');
        }; // Noncompliant - empty statement
    }
}
```

## Compliant solution

```apex
public class DataProcessor {
    public void process(List<Account> accounts) {
        for (Account acc : accounts) {
            acc.Name = acc.Name.trim();
        }

        if (accounts.size() > 0) {
            System.debug('Done');
        }
    }
}
```
