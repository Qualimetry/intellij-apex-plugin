# Restrict use of the global access modifier

`qa-convention-no-global-modifier` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

The global access modifier exposes classes and methods to all Apex code across all namespaces, including managed packages. In most cases public provides sufficient visibility. Overusing global enlarges the API surface, making future refactoring difficult because external consumers may depend on any global member.

## Noncompliant code example

```apex
global class MyService { // Noncompliant - global not needed
    global static void doWork() { // Noncompliant - global not needed
        System.debug('working');
    }

    public void process() {
        doWork();
    }

    public String getName() {
        return 'MyService';
    }
}
```

## Compliant solution

```apex
public class MyService {
    public static void doWork() {
        System.debug('working');
    }

    public void process() {
        doWork();
    }

    public String getName() {
        return 'MyService';
    }
}
```
