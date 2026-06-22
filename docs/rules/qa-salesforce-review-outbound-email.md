# Review outbound email via Messaging.sendEmail

`qa-salesforce-review-outbound-email` &middot; Salesforce &middot; Code Smell &middot; severity INFO &middot; enabled in the recommended profile

Sending outbound emails via Messaging.sendEmail() is subject to daily email limits (5,000 per org for single emails) and can inadvertently expose sensitive data. Each usage should be reviewed to ensure proper recipient validation, rate limiting, and that email content does not contain PII or debug information.

## Noncompliant code example

```apex
public class AlertService {
    public static void notifyAll(List<Contact> contacts, String body) {
        List<Messaging.SingleEmailMessage> emails =
            new List<Messaging.SingleEmailMessage>();
        for (Contact c : contacts) {
            Messaging.SingleEmailMessage mail = new Messaging.SingleEmailMessage();
            mail.setTargetObjectId(c.Id);
            mail.setPlainTextBody(body);
            emails.add(mail);
        }
        Messaging.sendEmail(emails);    // Noncompliant — review required
    }
}
```

## Compliant solution

```apex
public class AlertService {
    public static void notifyAll(List<Contact> contacts, String body) {
        // Reviewed: recipients validated, content sanitized,
        // batch size within daily limits
        List<Messaging.SingleEmailMessage> emails =
            new List<Messaging.SingleEmailMessage>();
        for (Contact c : contacts) {
            Messaging.SingleEmailMessage mail = new Messaging.SingleEmailMessage();
            mail.setTargetObjectId(c.Id);
            mail.setPlainTextBody(body);
            emails.add(mail);
        }
        Messaging.sendEmail(emails);
    }
}
```
