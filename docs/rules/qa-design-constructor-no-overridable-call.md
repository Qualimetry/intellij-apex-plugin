# Constructors must not invoke overridable methods

`qa-design-constructor-no-overridable-call` &middot; Design &middot; Code Smell &middot; severity CRITICAL &middot; enabled in the recommended profile

Calling a virtual or overridable method from a constructor is dangerous because the subclass override runs before the subclass constructor has completed. The overridden method may access fields that have not yet been initialized, leading to NullPointerException or corrupt state that is extremely difficult to diagnose.

## Noncompliant code example

```apex
public virtual class BaseService {
    private String config;

    public BaseService() {
        loadConfig(); // Noncompliant - calls overridable method from constructor
    }

    public virtual void loadConfig() {
        config = 'default';
    }
}

public class CustomService extends BaseService {
    private Map<String, String> settings;

    public CustomService() {
        super();
        settings = new Map<String, String>();
    }

    public override void loadConfig() {
        settings.put('key', 'val'); // NPE: settings is null during super()
    }
}
```

## Compliant solution

```apex
public virtual class BaseService {
    private String config;

    public BaseService() {
        doLoadConfig();
    }

    private void doLoadConfig() {
        config = 'default';
    }

    public virtual void loadConfig() {
        config = 'default';
    }
}
```
