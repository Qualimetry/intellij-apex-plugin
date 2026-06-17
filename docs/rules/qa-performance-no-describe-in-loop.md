# Schema describe calls must not execute inside loops

`qa-performance-no-describe-in-loop` &middot; Performance &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Schema describe calls (getDescribe(), getMap()) inside loops consume describe call governor limits and add significant CPU time per iteration. These metadata operations return the same results on every call within a transaction. Cache the describe result in a variable before the loop.

## Noncompliant code example

```apex
public class FieldValidator {
    public void validateFields(List<String> fieldNames) {
        for (String fieldName : fieldNames) {
            Map<String, Schema.SObjectField> fieldMap =
                Schema.SObjectType.Account.fields.getMap();   // Noncompliant — in loop
            if (fieldMap.containsKey(fieldName)) {
                Schema.DescribeFieldResult dfr =
                    fieldMap.get(fieldName).getDescribe();     // Noncompliant — in loop
            }
        }
    }
}
```

## Compliant solution

```apex
public class FieldValidator {
    public void validateFields(List<String> fieldNames) {
        Map<String, Schema.SObjectField> fieldMap =
            Schema.SObjectType.Account.fields.getMap();
        for (String fieldName : fieldNames) {
            if (fieldMap.containsKey(fieldName)) {
                Schema.DescribeFieldResult dfr =
                    fieldMap.get(fieldName).getDescribe();
            }
        }
    }
}
```
