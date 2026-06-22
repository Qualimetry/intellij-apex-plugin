# Aggregate functions require grouped SOQL queries

`qa-salesforce-aggregate-without-group` &middot; Salesforce &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Calling aggregate functions (SUM, AVG, COUNT, MAX, MIN) on SOQL results without using a GROUP BY clause produces misleading results — the aggregate operates on the entire result set as a single group. This is usually a logic error when the intent is to aggregate per category. Use GROUP BY to partition results before aggregation.

## Noncompliant code example

```apex
public class ReportService {
    public AggregateResult[] getSalesByRegion() {
        return [SELECT SUM(Amount) total
                FROM Opportunity];                // Noncompliant — no GROUP BY
    }
}
```

## Compliant solution

```apex
public class ReportService {
    public AggregateResult[] getSalesByRegion() {
        return [SELECT Region__c, SUM(Amount) total
                FROM Opportunity
                GROUP BY Region__c];
    }
}
```
