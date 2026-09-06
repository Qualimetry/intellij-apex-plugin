# Access Trigger context through iteration, not by index

`qa-salesforce-iterate-trigger-context` &middot; Salesforce &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Accessing Trigger.new and Trigger.old records by index (Trigger.new[0]) instead of iterating is fragile and fails when triggers process bulk operations. Salesforce triggers always receive collections that may contain 1 to 200 records. Always iterate over trigger context variables to handle bulk operations correctly.

## Noncompliant code example

```apex
trigger AccountTrigger on Account (before update) {
    Account oldAcc = Trigger.old[0];               // Noncompliant — index access
    Account newAcc = Trigger.new[0];               // Noncompliant
    if (oldAcc.Name != newAcc.Name) {
        newAcc.Description = 'Name changed';
    }
}
```

## Compliant solution

```apex
trigger AccountTrigger on Account (before update) {
    for (Account newAcc : Trigger.new) {
        Account oldAcc = Trigger.oldMap.get(newAcc.Id);
        if (oldAcc.Name != newAcc.Name) {
            newAcc.Description = 'Name changed';
        }
    }
}
```
