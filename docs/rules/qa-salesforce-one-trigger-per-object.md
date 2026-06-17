# Only one trigger should exist per SObject

`qa-salesforce-one-trigger-per-object` &middot; Salesforce &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Having multiple triggers on the same SObject makes execution order unpredictable, complicates debugging, and can cause conflicting logic to run in an undefined sequence. The Salesforce recommended pattern is one trigger per object that delegates to a handler class, ensuring deterministic execution and maintainability.

## Noncompliant code example

```apex
// AccountTrigger1.trigger
trigger AccountTrigger1 on Account (before insert) {    // Noncompliant — first trigger
    for (Account a : Trigger.new) {
        a.Description = 'Created';
    }
}

// AccountTrigger2.trigger
trigger AccountTrigger2 on Account (before insert) {    // Noncompliant — second trigger
    for (Account a : Trigger.new) {
        a.Rating = 'New';
    }
}
```

## Compliant solution

```apex
// AccountTrigger.trigger — single trigger per object
trigger AccountTrigger on Account (before insert) {
    AccountTriggerHandler.handleBeforeInsert(Trigger.new);
}
```
