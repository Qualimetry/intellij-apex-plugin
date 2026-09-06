# Constructors should invoke the parent constructor

`qa-convention-call-super-in-constructor` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

When a class extends another, its constructors should explicitly call super() or this() to ensure the parent class is properly initialized. Omitting the call relies on an implicit default super-constructor invocation, which may not exist or may not perform the expected setup. Explicit calls make the initialization chain visible and intentional.

## Noncompliant code example

```apex
public class Child extends Parent {
    private String name;

    public Child() { // Noncompliant - no super() call
        this.name = 'default';
    }

    public Child(String n) { // Noncompliant - no super() call
        this.name = n;
    }

    public void process() { }
}
```

## Compliant solution

```apex
public class Child extends Parent {
    private String name;

    public Child() {
        super();
        this.name = 'default';
    }

    public Child(String n) {
        super();
        this.name = n;
    }

    public void process() { }
}
```
