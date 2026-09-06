# Use typed parameters instead of generic Object

`qa-complexity-use-typed-parameters` &middot; Complexity &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Methods that accept Object parameters instead of specific types lose compile-time type safety, force consumers to perform error-prone casting, and make the API harder to understand. Use concrete types or generics to make the method signature self-documenting and let the compiler catch type mismatches.

## Noncompliant code example

```apex
public class DataProcessor {
    public void process(Object data) {             // Noncompliant — use typed parameter
        if (data instanceof Account) {
            processAccount((Account) data);
        } else if (data instanceof Contact) {
            processContact((Contact) data);
        }
    }
}
```

## Compliant solution

```apex
public class DataProcessor {
    public void processAccount(Account acc) {
        // type-safe, self-documenting
    }

    public void processContact(Contact con) {
        // type-safe, self-documenting
    }
}
```
