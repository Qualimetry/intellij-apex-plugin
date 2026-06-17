# Trigger names should not exceed maximum length

`qa-convention-trigger-name-max-length` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Excessively long trigger names are difficult to reference in documentation, logs, and configuration files. Long names also create awkward line breaks in trigger declarations. Keep trigger names concise while clearly conveying the SObject and purpose.

## Noncompliant code example

```apex
// Noncompliant - trigger name exceeds 40 characters
trigger ThisIsAnExtremelyLongTriggerNameForAccounts on Account (before insert, before update) {
    for (Account acc : Trigger.new) {
        if (acc.Name == null) {
            acc.Name = 'Default';
        }
    }
}
```

## Compliant solution

```apex
trigger AccountValidation on Account (before insert, before update) {
    for (Account acc : Trigger.new) {
        if (acc.Name == null) {
            acc.Name = 'Default';
        }
    }
}
```
