# Override both equals() and hashCode()

`qa-error-equals-without-hashcode` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

This rule flags classes that override equals() without overriding hashCode(), or vice versa. The two methods form a contract: objects that are equal must produce the same hash code. Breaking this contract causes incorrect behavior when instances are stored in hash-based collections such as Set or Map, leading to duplicates or failed lookups.

## Noncompliant code example

```apex
public class ContactKey { // Noncompliant — equals without hashCode
    private String email;

    public ContactKey(String email) {
        this.email = email;
    }

    public Boolean equals(Object other) {
        if (other instanceof ContactKey) {
            return this.email == ((ContactKey) other).email;
        }
        return false;
    }

    // Missing: Integer hashCode()
}
```

## Compliant solution

```apex
public class ContactKey {
    private String email;

    public ContactKey(String email) {
        this.email = email;
    }

    public Boolean equals(Object other) {
        if (other instanceof ContactKey) {
            return this.email == ((ContactKey) other).email;
        }
        return false;
    }

    public Integer hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
```
