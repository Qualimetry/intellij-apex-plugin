# System.debug should specify a LoggingLevel

`qa-bestpractice-debug-logging-level` &middot; Best Practice &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

This rule detects calls to System.debug() that omit the LoggingLevel parameter. Without an explicit level, all debug statements default to DEBUG, making it impossible to filter log output by severity. Specifying a level (e.g. LoggingLevel.FINE, LoggingLevel.ERROR) enables selective logging and reduces noise when troubleshooting production issues.

## Noncompliant code example

```apex
public class AccountService {
    public void processAccounts(List<Account> accounts) {
        System.debug('Starting account processing'); // Noncompliant
        for (Account a : accounts) {
            a.Description = 'Processed';
            System.debug('Processing: ' + a.Name); // Noncompliant
        }
        update accounts;
        System.debug('Finished processing ' + accounts.size() + ' accounts'); // Noncompliant
    }
}
```

## Compliant solution

```apex
public class AccountService {
    public void processAccounts(List<Account> accounts) {
        System.debug(LoggingLevel.INFO, 'Starting account processing');
        for (Account a : accounts) {
            a.Description = 'Processed';
            System.debug(LoggingLevel.FINE, 'Processing: ' + a.Name);
        }
        update accounts;
        System.debug(LoggingLevel.INFO, 'Finished processing ' + accounts.size() + ' accounts');
    }
}
```
