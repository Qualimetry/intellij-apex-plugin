# Switch statements must have a minimum number of branches

`qa-design-switch-min-branches` &middot; Design &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

A switch statement with only one or two branches provides no advantage over a simple if/else. Switch is designed for multi-way branching; using it with too few cases adds syntactic overhead without the readability benefit that switch provides for larger decision trees.

## Noncompliant code example

```apex
public class StatusMapper {
    public String getLabel(String status) {
        switch on status { // Noncompliant - too few branches
            when 'Active' {
                return 'Active';
            }
            when else {
                return 'Unknown';
            }
        }
    }
}
```

## Compliant solution

```apex
public class StatusMapper {
    public String getLabel(String status) {
        if (status == 'Active') {
            return 'Active';
        }
        return 'Unknown';
    }
}
```
