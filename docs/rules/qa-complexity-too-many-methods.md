# Classes must not declare too many methods

`qa-complexity-too-many-methods` &middot; Complexity &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Classes with too many methods are likely handling multiple unrelated responsibilities. A high method count increases the class surface area, makes it harder to understand, and creates excessive coupling between the class and its consumers. Split the class into focused components that each handle a single domain.

## Noncompliant code example

```apex
public class UtilityService {                      // Noncompliant — too many methods
    public void createAccount() { }
    public void deleteAccount() { }
    public void sendEmail() { }
    public void generatePdf() { }
    public void syncData() { }
    public void validateInput() { }
    public void formatCurrency() { }
    public void parseDate() { }
    // ... 20+ methods
}
```

## Compliant solution

```apex
public class AccountService {
    public void createAccount() { }
    public void deleteAccount() { }
}

public class NotificationService {
    public void sendEmail() { }
}

public class FormatHelper {
    public String formatCurrency(Decimal amount) { return null; }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| threshold | Maximum number of methods per class | `40` |
