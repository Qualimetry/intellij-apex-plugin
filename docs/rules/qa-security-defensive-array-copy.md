# Externally provided arrays must be defensively copied

`qa-security-defensive-array-copy` &middot; Security &middot; Security Hotspot &middot; severity MAJOR &middot; enabled in the recommended profile

Storing a reference to an externally provided array or collection directly allows the caller to modify the object's internal state after passing it in. This breaks encapsulation and can lead to unexpected behavior, data corruption, or security bypasses. Always create a defensive copy before storing the reference.

## Noncompliant code example

```apex
public class AccountProcessor {
    private List<Id> accountIds;

    public void setAccountIds(List<Id> ids) {
        this.accountIds = ids;    // Noncompliant — caller retains a live reference
    }

    public void process() {
        for (Id aid : accountIds) {
            // caller could have modified the list after setAccountIds()
        }
    }
}
```

## Compliant solution

```apex
public class AccountProcessor {
    private List<Id> accountIds;

    public void setAccountIds(List<Id> ids) {
        this.accountIds = ids != null ? new List<Id>(ids) : new List<Id>();
    }

    public void process() {
        for (Id aid : accountIds) {
            // internal list is isolated from external changes
        }
    }
}
```
