# Review usage of //NOSONAR

`qa-unused-nosonar-comment` &middot; Unused Code &middot; Code Smell &middot; severity INFO &middot; enabled in the recommended profile

//NOSONAR comments suppress analysis findings without explaining why. Excessive use of suppression comments hides technical debt and prevents legitimate issues from being addressed. Each //NOSONAR usage should be reviewed to determine whether the underlying issue should be fixed rather than suppressed, and if suppression is necessary, it should include a justification.

## Noncompliant code example

```apex
public class LegacyService {
    public void process() {
        System.debug('sensitive data');  //NOSONAR           // Noncompliant — review needed
        String q = 'SELECT Id FROM Account WHERE Name = \'' + input + '\''; //NOSONAR  // Noncompliant
    }
}
```

## Compliant solution

```apex
public class LegacyService {
    public void process() {
        // Fix the underlying issues instead of suppressing
        Logger.info('Processing started');
        String q = 'SELECT Id FROM Account WHERE Name = \''
            + String.escapeSingleQuotes(input) + '\'';
    }
}
```
