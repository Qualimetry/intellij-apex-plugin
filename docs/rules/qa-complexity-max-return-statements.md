# Methods must limit the number of return points

`qa-complexity-max-return-statements` &middot; Complexity &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Methods with too many return points are harder to follow because the reader must track multiple exit conditions. While early returns for guard clauses are acceptable, excessive return statements throughout a method body indicate that the method is doing too much and should be decomposed.

## Noncompliant code example

```apex
public class StatusResolver {
    public String resolve(Record__c rec) {         // Noncompliant — too many returns
        if (rec == null) return 'Unknown';
        if (rec.IsDeleted__c) return 'Deleted';
        if (rec.IsArchived__c) return 'Archived';
        if (rec.Status__c == 'A') return 'Active';
        if (rec.Status__c == 'B') return 'Inactive';
        if (rec.Status__c == 'C') return 'Pending';
        return 'Other';
    }
}
```

## Compliant solution

```apex
public class StatusResolver {
    private static final Map<String, String> STATUS_MAP = new Map<String, String>{
        'A' => 'Active', 'B' => 'Inactive', 'C' => 'Pending'
    };

    public String resolve(Record__c rec) {
        if (rec == null) return 'Unknown';
        if (rec.IsDeleted__c) return 'Deleted';
        if (rec.IsArchived__c) return 'Archived';
        return STATUS_MAP.containsKey(rec.Status__c)
            ? STATUS_MAP.get(rec.Status__c) : 'Other';
    }
}
```
