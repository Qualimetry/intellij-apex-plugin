# Eagerly loaded DescribeSObjectResult wastes resources

`qa-performance-eager-describe` &middot; Performance &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

This rule flags calls to Schema.getGlobalDescribe() and similar eager-loading describe methods. The global describe loads metadata for every SObject in the org into memory, consuming significant heap space and CPU time. In most cases only a small number of SObject types are needed; target those directly using SObjectType tokens instead.

## Noncompliant code example

```apex
public class MetadataService {
    public List<String> getAllObjectNames() {
        Map<String, Schema.SObjectType> gd = Schema.getGlobalDescribe(); // Noncompliant
        List<String> names = new List<String>();
        for (String key : gd.keySet()) {
            names.add(key);
        }
        return names;
    }

    public Schema.DescribeSObjectResult describeAny(String objName) {
        Map<String, Schema.SObjectType> gd = Schema.getGlobalDescribe(); // Noncompliant
        return gd.get(objName).getDescribe();
    }
}
```

## Compliant solution

```apex
public class MetadataService {
    public Schema.DescribeSObjectResult describeAccount() {
        return Account.SObjectType.getDescribe();
    }

    public Boolean isFieldAccessible(String fieldName) {
        Schema.DescribeFieldResult dfr = Account.SObjectType.getDescribe()
            .fields.getMap().get(fieldName).getDescribe();
        return dfr.isAccessible();
    }
}
```
