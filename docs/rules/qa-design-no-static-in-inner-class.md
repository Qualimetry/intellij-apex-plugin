# Inner classes must not declare static members

`qa-design-no-static-in-inner-class` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Inner classes in Apex cannot declare static members because they are tied to an instance of the enclosing class. Attempting to use static fields or methods in an inner class violates the language specification and leads to compilation errors or unexpected runtime behavior.

## Noncompliant code example

```apex
public class OuterService {
    public class InnerHelper {
        static Integer counter = 0; // Noncompliant - static in inner class
        static String label = 'test'; // Noncompliant - static in inner class

        public Integer getCount() {
            return counter;
        }
    }
}
```

## Compliant solution

```apex
public class OuterService {
    private static Integer counter = 0;
    private static String label = 'test';

    public class InnerHelper {
        public Integer getCount() {
            return counter;
        }
    }
}
```
