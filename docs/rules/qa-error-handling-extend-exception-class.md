# Custom exception classes must extend System.Exception

`qa-error-handling-extend-exception-class` &middot; Error Handling &middot; Code Smell &middot; severity CRITICAL &middot; enabled in the recommended profile

Custom exception classes in Apex must extend Exception (or one of its subclasses). A class named with the Exception suffix that does not extend the exception hierarchy will not work with throw and catch statements, causing compilation errors or unexpected behavior.

## Noncompliant code example

```apex
public class PaymentException { // Noncompliant - does not extend Exception
    public String message;

    public PaymentException(String msg) {
        this.message = msg;
    }
}

public class ValidationException { // Noncompliant - does not extend Exception
    public String errorCode;
}
```

## Compliant solution

```apex
public class PaymentException extends Exception {
}

public class ValidationException extends Exception {
}
```
