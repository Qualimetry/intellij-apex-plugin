# Variable names must accurately describe their purpose

`qa-convention-misleading-variable-name` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Variable names like l, o, temp, or data convey no useful information about the variable's purpose. Single-letter names are easily confused with digits or other identifiers, and generic names force readers to trace assignments to understand what the variable actually holds. Choose names that describe the content or role.

## Noncompliant code example

```apex
public class AccountProcessor {
    public void process(List<Account> accounts) {
        Object data = accounts; // Noncompliant - 'data' is too generic
        for (Account acc : accounts) {
            acc.Description = 'processed';
        }
        String temp = 'value'; // Noncompliant - 'temp' is too generic
        System.debug(temp);
    }
}
```

## Compliant solution

```apex
public class AccountProcessor {
    public void process(List<Account> accounts) {
        Object accountRecords = accounts;
        for (Account acc : accounts) {
            acc.Description = 'processed';
        }
        String statusLabel = 'value';
        System.debug(statusLabel);
    }
}
```
