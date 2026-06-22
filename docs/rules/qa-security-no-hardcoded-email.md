# Email addresses must not be hardcoded

`qa-security-no-hardcoded-email` &middot; Security &middot; Security Hotspot &middot; severity BLOCKER &middot; enabled in the recommended profile

Hardcoding email addresses in source code makes them difficult to change across environments (sandbox vs. production), exposes internal addresses to anyone with code access, and violates the principle of configuration externalization. Use Custom Settings, Custom Metadata Types, or Custom Labels to store email addresses so they can be updated without deploying code.

## Noncompliant code example

```apex
public class NotificationService {
    public static void sendAlert(String message) {
        Messaging.SingleEmailMessage mail = new Messaging.SingleEmailMessage();
        mail.setToAddresses(new String[]{ 'admin@company.com' });  // Noncompliant
        mail.setSubject('System Alert');
        mail.setPlainTextBody(message);
        Messaging.sendEmail(new List<Messaging.SingleEmailMessage>{ mail });
    }

    public static void notifySupport() {
        String support = 'support@company.com';  // Noncompliant
        sendTo(support);
    }
}
```

## Compliant solution

```apex
public class NotificationService {
    public static void sendAlert(String message) {
        AppConfig__c config = AppConfig__c.getInstance();
        Messaging.SingleEmailMessage mail = new Messaging.SingleEmailMessage();
        mail.setToAddresses(new String[]{ config.AlertEmail__c });
        mail.setSubject('System Alert');
        mail.setPlainTextBody(message);
        Messaging.sendEmail(new List<Messaging.SingleEmailMessage>{ mail });
    }
}
```
