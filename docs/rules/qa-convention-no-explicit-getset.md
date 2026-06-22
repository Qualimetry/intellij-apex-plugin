# Prefer properties over explicit get/set methods

`qa-convention-no-explicit-getset` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Apex provides built-in property syntax (get; set;) that eliminates the need for explicit getter and setter methods. Using explicit getX() / setX() methods instead of properties is verbose and inconsistent with Apex idioms. Properties reduce boilerplate and make the intent clearer, especially in Visualforce bindings and Lightning components.

## Noncompliant code example

```apex
public class ContactWrapper {
    private String firstName;
    private String lastName;

    public String getFirstName() { // Noncompliant - use a property instead
        return firstName;
    }

    public void setFirstName(String value) {
        firstName = value;
    }

    public String getLastName() {
        return lastName;
    }
}
```

## Compliant solution

```apex
public class ContactWrapper {
    public String firstName { get; set; }
    public String lastName { get; set; }
}
```
