# Interfaces must not be used solely for constants

`qa-convention-no-constants-interface` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Interfaces should define a behavioral contract, not serve as a container for constant values. When an interface contains only constants and no methods, classes that implement it inherit the constants into their public API, which pollutes their namespace and tightly couples them to an implementation detail. Use a dedicated class with a private constructor or an enum instead.

## Noncompliant code example

```apex
public interface AppConstants { // Noncompliant - interface used solely for constants
    String STATUS_ACTIVE = 'Active';
    String STATUS_INACTIVE = 'Inactive';
    Integer MAX_RETRIES = 3;
    Integer BATCH_SIZE = 200;
}

public class OrderService implements AppConstants {
    public void process() {
        if (retries < MAX_RETRIES) {
            retry();
        }
    }
}
```

## Compliant solution

```apex
public class AppConstants {
    public static final String STATUS_ACTIVE = 'Active';
    public static final String STATUS_INACTIVE = 'Inactive';
    public static final Integer MAX_RETRIES = 3;
    public static final Integer BATCH_SIZE = 200;

    private AppConstants() { }
}

public class OrderService {
    public void process() {
        if (retries < AppConstants.MAX_RETRIES) {
            retry();
        }
    }
}
```
