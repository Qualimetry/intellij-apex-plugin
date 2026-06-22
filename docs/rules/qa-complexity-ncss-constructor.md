# Constructor NCSS must not exceed threshold

`qa-complexity-ncss-constructor` &middot; Complexity &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Non-Commenting Source Statements (NCSS) counts the executable lines in a constructor, excluding blank lines and comments. Constructors with high NCSS are doing too much initialization work, making them hard to understand and test. Move complex initialization logic into factory methods or builder patterns.

## Noncompliant code example

```apex
public class DashboardController {
    public DashboardController() {                 // Noncompliant — NCSS exceeds threshold
        accounts = [SELECT Id, Name FROM Account LIMIT 100];
        contacts = [SELECT Id FROM Contact LIMIT 100];
        opportunities = [SELECT Id FROM Opportunity LIMIT 100];
        tasks = [SELECT Id FROM Task LIMIT 100];
        events = [SELECT Id FROM Event LIMIT 100];
        // ... 30+ lines of initialization
    }
}
```

## Compliant solution

```apex
public class DashboardController {
    public DashboardController() {
        loadData();
    }

    private void loadData() {
        accounts = AccountRepository.getRecent();
        contacts = ContactRepository.getRecent();
    }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| threshold | Maximum NCSS per constructor | `15` |
