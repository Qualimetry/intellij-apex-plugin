# Classes must not accumulate too many responsibilities

`qa-complexity-god-class` &middot; Complexity &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A "God Class" accumulates too many responsibilities, combining unrelated functionality into a single class. This violates the Single Responsibility Principle, making the class hard to understand, test, and modify without unintended side effects. God classes are identified by a combination of high method count, high field count, and low cohesion. Refactor by extracting cohesive groups of fields and methods into dedicated classes.

## Noncompliant code example

```apex
public class ApplicationManager {                  // Noncompliant — God Class
    private List<Account> accounts;
    private List<Contact> contacts;
    private Map<Id, Opportunity> opportunities;
    private List<Task> tasks;
    private EmailService emailService;

    public void createAccount() { }
    public void deleteAccount() { }
    public void createContact() { }
    public void sendEmail() { }
    public void generateReport() { }
    public void syncToExternal() { }
    // ... 20+ methods spanning unrelated domains
}
```

## Compliant solution

```apex
public class AccountService {
    public void createAccount() { }
    public void deleteAccount() { }
}

public class ContactService {
    public void createContact() { }
}

public class ReportService {
    public void generateReport() { }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| maxFields | Maximum number of fields before a class is flagged as a God Class | `15` |
| maxMethods | Maximum number of methods before a class is flagged as a God Class | `25` |
