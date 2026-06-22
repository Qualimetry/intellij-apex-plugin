# Unused method parameters should be removed

`qa-unused-formal-parameter` &middot; Unused Code &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags method parameters that are declared but never referenced in the method body. Unused parameters bloat method signatures and mislead callers about what data the method actually requires. Remove them or use them. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class NotificationService {
    public void sendAlert(String message, Integer priority) { // Noncompliant — priority unused
        Messaging.SingleEmailMessage mail = new Messaging.SingleEmailMessage();
        mail.setSubject('Alert');
        mail.setPlainTextBody(message);
        Messaging.sendEmail(new List<Messaging.SingleEmailMessage>{ mail });
    }
}
```

## Compliant solution

```apex
public class NotificationService {
    public void sendAlert(String message) {
        Messaging.SingleEmailMessage mail = new Messaging.SingleEmailMessage();
        mail.setSubject('Alert');
        mail.setPlainTextBody(message);
        Messaging.sendEmail(new List<Messaging.SingleEmailMessage>{ mail });
    }
}
```
