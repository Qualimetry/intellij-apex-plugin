# Switch should not pack too many statements per case

`qa-complexity-switch-density` &middot; Complexity &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Switch statements with too many statements packed into each case clause are hard to read and maintain. Dense case blocks indicate that each case contains complex logic that should be extracted into dedicated methods. Keep case blocks concise by delegating to well-named handler methods.

## Noncompliant code example

```apex
public class EventHandler {
    public void handle(String eventType) {
        switch on eventType {                      // Noncompliant — high density
            when 'CREATE' {
                Account acc = new Account();
                acc.Name = 'New';
                acc.Industry = 'Tech';
                acc.Description = 'Auto-created';
                insert acc;
                notifyTeam(acc);
                logEvent('CREATE', acc.Id);
            }
            when 'DELETE' {
                // ... 10+ lines
            }
        }
    }
}
```

## Compliant solution

```apex
public class EventHandler {
    public void handle(String eventType) {
        switch on eventType {
            when 'CREATE' { handleCreate(); }
            when 'DELETE' { handleDelete(); }
        }
    }

    private void handleCreate() {
        Account acc = new Account(Name = 'New', Industry = 'Tech');
        insert acc;
        notifyTeam(acc);
    }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| threshold | Maximum number of statements per case clause | `5` |
