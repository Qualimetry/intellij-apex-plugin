# @Future methods must not be invoked inside loops

`qa-salesforce-no-future-in-loop` &middot; Salesforce &middot; Bug &middot; severity CRITICAL &middot; optional

This rule detects @Future method invocations inside loop bodies. Each call to an @Future method counts against the asynchronous Apex governor limit per transaction. Invoking them in a loop can quickly exhaust this limit and cause runtime exceptions. Collect the work and make a single asynchronous call outside the loop. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class NotificationService {
    public void notifyAll(List<Contact> contacts) {
        for (Contact c : contacts) {
            sendNotificationAsync(c.Id); // Noncompliant — @Future in loop
        }
    }

    @Future
    public static void sendNotificationAsync(Id contactId) {
        // send notification
    }
}
```

## Compliant solution

```apex
public class NotificationService {
    public void notifyAll(List<Contact> contacts) {
        Set<Id> contactIds = new Set<Id>();
        for (Contact c : contacts) {
            contactIds.add(c.Id);
        }
        sendNotificationsAsync(contactIds);
    }

    @Future
    public static void sendNotificationsAsync(Set<Id> contactIds) {
        // send notifications in bulk
    }
}
```
