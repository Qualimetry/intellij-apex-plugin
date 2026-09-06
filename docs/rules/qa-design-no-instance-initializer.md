# Instance initializer blocks should be replaced with constructors

`qa-design-no-instance-initializer` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Instance initializer blocks run before the constructor body but after the super constructor, creating a confusing initialization order. Most developers expect all initialization to occur in the constructor. Moving the logic into an explicit constructor makes the initialization sequence clear and predictable.

## Noncompliant code example

```apex
public class DataLoader {
    private List<String> records;
    private Boolean isReady;

    { // Noncompliant - instance initializer block
        records = new List<String>();
        isReady = false;
    }

    public void load() {
        records.add('Record1');
        isReady = true;
    }
}
```

## Compliant solution

```apex
public class DataLoader {
    private List<String> records;
    private Boolean isReady;

    public DataLoader() {
        records = new List<String>();
        isReady = false;
    }

    public void load() {
        records.add('Record1');
        isReady = true;
    }
}
```
