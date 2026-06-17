# Replace element-by-element loops with addAll()

`qa-design-use-addall` &middot; Design &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Iterating over a collection only to call .add() on each element is verbose and inefficient. The addAll() method accomplishes the same result in a single call, improving readability and reducing the chance of errors in the loop body.

## Noncompliant code example

```apex
public class ContactMerger {
    public List<Contact> mergeContacts(List<Contact> primary, List<Contact> secondary) {
        List<Contact> merged = new List<Contact>();
        for (Contact c : primary) { // Noncompliant - use addAll() instead
            merged.add(c);
        }
        for (Contact c : secondary) { // Noncompliant - use addAll() instead
            merged.add(c);
        }
        return merged;
    }
}
```

## Compliant solution

```apex
public class ContactMerger {
    public List<Contact> mergeContacts(List<Contact> primary, List<Contact> secondary) {
        List<Contact> merged = new List<Contact>();
        merged.addAll(primary);
        merged.addAll(secondary);
        return merged;
    }
}
```
