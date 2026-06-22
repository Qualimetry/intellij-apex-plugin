# Methods must return copies of internal arrays

`qa-security-no-return-internal-array` &middot; Security &middot; Security Hotspot &middot; severity MAJOR &middot; enabled in the recommended profile

Returning a direct reference to an internal array or collection allows callers to modify the object's private state, breaking encapsulation and potentially introducing security vulnerabilities or data corruption. Return a defensive copy using new List<T>(list) or clone() instead.

## Noncompliant code example

```apex
public class RoleManager {
    private List<String> adminRoles = new List<String>{ 'Admin', 'SuperAdmin' };

    public List<String> getAdminRoles() {
        return adminRoles;    // Noncompliant — caller can add/remove roles
    }

    public List<Id> getAccountIds() {
        return this.accountIds;  // Noncompliant
    }
}
```

## Compliant solution

```apex
public class RoleManager {
    private List<String> adminRoles = new List<String>{ 'Admin', 'SuperAdmin' };

    public List<String> getAdminRoles() {
        return new List<String>(adminRoles);
    }

    public List<Id> getAccountIds() {
        return this.accountIds != null
            ? new List<Id>(this.accountIds) : new List<Id>();
    }
}
```
