# Controller state variables should be marked transient

`qa-salesforce-transient-controller-state` &middot; Salesforce &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Visualforce controllers serialize their state into the view state on every page interaction. Large non-transient fields inflate view state size, degrading page performance and risking the 170 KB view state limit. Mark fields that do not need to survive postbacks as transient to exclude them from serialization.

## Noncompliant code example

```apex
public class ReportController {
    public List<AggregateResult> reportData { get; set; }   // Noncompliant — should be transient
    public Map<Id, Account> accountCache { get; set; }       // Noncompliant

    public ReportController() {
        reportData = [SELECT COUNT(Id) cnt, Industry
                      FROM Account GROUP BY Industry];
        accountCache = new Map<Id, Account>(
            [SELECT Id, Name FROM Account LIMIT 1000]);
    }
}
```

## Compliant solution

```apex
public class ReportController {
    public transient List<AggregateResult> reportData { get; set; }
    public transient Map<Id, Account> accountCache { get; set; }

    public ReportController() {
        reportData = [SELECT COUNT(Id) cnt, Industry
                      FROM Account GROUP BY Industry];
        accountCache = new Map<Id, Account>(
            [SELECT Id, Name FROM Account LIMIT 1000]);
    }
}
```
