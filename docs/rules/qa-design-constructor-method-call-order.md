# Constructor method calls must follow field initialization

`qa-design-constructor-method-call-order` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Calling methods in a constructor before initializing fields can lead to subtle bugs where the method operates on uninitialized or default-valued state. Field assignments should always precede method calls so that the object is in a consistent state before any logic runs against it.

## Noncompliant code example

```apex
public class OrderProcessor {
    private Integer maxRetries;
    private String endpoint;

    public OrderProcessor() {
        configure(); // Noncompliant - method called before fields are initialized
        maxRetries = 3;
        endpoint = 'https://api.example.com';
    }

    private void configure() {
        System.debug('max=' + maxRetries);
    }
}
```

## Compliant solution

```apex
public class OrderProcessor {
    private Integer maxRetries;
    private String endpoint;

    public OrderProcessor() {
        maxRetries = 3;
        endpoint = 'https://api.example.com';
        configure();
    }

    private void configure() {
        System.debug('max=' + maxRetries);
    }
}
```
