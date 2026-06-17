# Trigger bodies must not contain business logic

`qa-salesforce-no-trigger-logic` &middot; Salesforce &middot; Code Smell &middot; severity MAJOR &middot; optional

This rule flags trigger bodies that contain business logic instead of delegating to a handler class. Logic in triggers is difficult to test, reuse, and maintain. Follow the "one trigger per object" pattern and delegate all logic to an Apex handler class. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
trigger AccountTrigger on Account (before insert, before update) { // Noncompliant
    for (Account a : Trigger.new) {
        if (a.AnnualRevenue > 1000000) {
            a.Rating = 'Hot';
        } else {
            a.Rating = 'Cold';
        }
    }
}
```

## Compliant solution

```apex
trigger AccountTrigger on Account (before insert, before update) {
    AccountTriggerHandler.handleBeforeInsertUpdate(Trigger.new);
}
```
