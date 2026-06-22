# Email must be sent outside of loop bodies

`qa-salesforce-no-email-in-loop` &middot; Salesforce &middot; Bug &middot; severity MAJOR &middot; optional

This rule detects Messaging.sendEmail() calls inside loop bodies. Each call counts against the email governor limit per transaction. Collect all email messages into a list and send them in a single bulk call outside the loop. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class NotificationSender {
    public void sendNotifications(List<Contact> contacts) {
        for (Contact c : contacts) {
            Messaging.SingleEmailMessage mail = new Messaging.SingleEmailMessage();
            mail.setToAddresses(new String[]{ c.Email });
            mail.setSubject('Update');
            Messaging.sendEmail(new Messaging.SingleEmailMessage[]{ mail }); // Noncompliant
        }
    }
}
```

## Compliant solution

```apex
public class NotificationSender {
    public void sendNotifications(List<Contact> contacts) {
        List<Messaging.SingleEmailMessage> mails = new List<Messaging.SingleEmailMessage>();
        for (Contact c : contacts) {
            Messaging.SingleEmailMessage mail = new Messaging.SingleEmailMessage();
            mail.setToAddresses(new String[]{ c.Email });
            mail.setSubject('Update');
            mails.add(mail);
        }
        Messaging.sendEmail(mails);
    }
}
```
