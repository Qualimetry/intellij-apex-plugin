# Custom class names must not match Salesforce system class names

`qa-convention-no-system-class-name` &middot; Convention &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Naming a custom class after a Salesforce system class (such as Account, User, or Trigger) creates ambiguity throughout the codebase. Developers and tools cannot easily determine whether a reference points to the platform type or the custom class, leading to compilation errors, unexpected runtime behavior, and confusing code navigation.

## Noncompliant code example

```apex
public class Account { // Noncompliant - shadows system class Account
    private String name;

    public void process() {
        name = 'test';
    }
}

public class User { // Noncompliant - shadows system class User
    public void run() { }
}
```

## Compliant solution

```apex
public class CustomAccount {
    private String name;

    public void process() {
        name = 'test';
    }
}

public class AppUser {
    public void run() { }
}
```
