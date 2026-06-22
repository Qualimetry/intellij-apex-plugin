# Method NPath complexity must not exceed threshold

`qa-complexity-npath` &middot; Complexity &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

NPath complexity measures the total number of acyclic execution paths through a method. It grows exponentially with nested conditionals — a method with 5 independent if statements has 32 paths (2^5). High NPath complexity means the method requires an impractical number of test cases to achieve full path coverage. Decompose the method into simpler functions.

## Noncompliant code example

```apex
public class Validator {
    public Boolean validate(Record__c rec) {       // Noncompliant — NPath exceeds threshold
        if (rec.Name != null) { /* path A */ }
        if (rec.Status__c != null) { /* path B */ }
        if (rec.Amount__c > 0) { /* path C */ }
        if (rec.OwnerId != null) { /* path D */ }
        if (rec.Region__c != null) { /* path E */ }
        if (rec.Type__c != null) { /* path F */ }
        if (rec.Priority__c != null) { /* path G */ }
        return true;
    }
}
```

## Compliant solution

```apex
public class Validator {
    public Boolean validate(Record__c rec) {
        return validateRequired(rec) && validateBusiness(rec);
    }

    private Boolean validateRequired(Record__c rec) {
        return rec.Name != null && rec.Status__c != null;
    }

    private Boolean validateBusiness(Record__c rec) {
        return rec.Amount__c > 0 && rec.OwnerId != null;
    }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| threshold | Maximum NPath complexity per method | `200` |
