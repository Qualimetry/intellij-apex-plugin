# Assert statements should only appear in test methods

`qa-testing-no-asserts-in-production` &middot; Testing &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Assert statements (System.assert, Assert.isTrue, etc.) in non-test methods serve no useful purpose in production — they do not run in production context and their presence confuses the boundary between production code and test code. Move assertions into test methods where they can properly verify behavior.

## Noncompliant code example

```apex
public class AccountService {
    public void createAccount(String name) {
        System.assert(String.isNotBlank(name), 'Name required');  // Noncompliant
        Account acc = new Account(Name = name);
        insert acc;
        Assert.isNotNull(acc.Id, 'Insert succeeded');              // Noncompliant
    }
}
```

## Compliant solution

```apex
public class AccountService {
    public void createAccount(String name) {
        if (String.isBlank(name)) {
            throw new IllegalArgumentException('Name is required');
        }
        Account acc = new Account(Name = name);
        insert acc;
    }
}
```
