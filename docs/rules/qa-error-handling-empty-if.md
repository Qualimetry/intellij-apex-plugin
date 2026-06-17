# If blocks must contain at least one statement

`qa-error-handling-empty-if` &middot; Error Handling &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

An empty if block indicates dead code, an incomplete implementation, or an accidental semicolon after the condition. The condition is evaluated but the result is silently ignored, which wastes CPU cycles and confuses readers about the developer's intent.

## Noncompliant code example

```apex
public class AccountFilter {
    public void filterAccounts(List<Account> accounts) {
        for (Account acc : accounts) {
            if (acc.IsActive__c) { // Noncompliant - empty if block
            }
        }
    }

    public void validate(String input) {
        if (String.isNotBlank(input)) { // Noncompliant - empty if block
        }
    }
}
```

## Compliant solution

```apex
public class AccountFilter {
    public void filterAccounts(List<Account> accounts) {
        for (Account acc : accounts) {
            if (acc.IsActive__c) {
                processAccount(acc);
            }
        }
    }

    public void validate(String input) {
        if (String.isNotBlank(input)) {
            System.debug('Valid input: ' + input);
        }
    }
    private void processAccount(Account acc) { }
}
```
