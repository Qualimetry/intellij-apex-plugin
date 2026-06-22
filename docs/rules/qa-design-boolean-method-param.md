# Boolean method parameters reduce API clarity

`qa-design-boolean-method-param` &middot; Design &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

This rule flags method signatures that accept Boolean parameters. Boolean parameters make call sites ambiguous—readers cannot determine the meaning of true or false without consulting the method signature. Prefer separate methods with descriptive names, or use an enum or options class to convey intent clearly.

## Noncompliant code example

```apex
public class ReportGenerator {
    public void generateReport(Boolean includeArchived) { // Noncompliant
        List<Case> cases;
        if (includeArchived) {
            cases = [SELECT Id, Subject FROM Case];
        } else {
            cases = [SELECT Id, Subject FROM Case WHERE IsArchived__c = false];
        }
        buildPdf(cases);
    }

    public void sendReport(Boolean async, Boolean compress) { // Noncompliant
        // unclear at call site: sendReport(true, false)
    }
}
```

## Compliant solution

```apex
public class ReportGenerator {
    public void generateActiveReport() {
        List<Case> cases = [SELECT Id, Subject FROM Case WHERE IsArchived__c = false];
        buildPdf(cases);
    }

    public void generateFullReport() {
        List<Case> cases = [SELECT Id, Subject FROM Case];
        buildPdf(cases);
    }

    public void sendReport(ReportOptions options) {
        // options.isAsync, options.isCompressed — intent is explicit
    }
}
```
