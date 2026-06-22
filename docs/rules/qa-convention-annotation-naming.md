# Annotations should use consistent casing

`qa-convention-annotation-naming` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

This rule flags annotations that do not start with an uppercase letter, which violates the standard Apex annotation casing convention. Annotations such as @AuraEnabled, @IsTest, and @Future all use PascalCase. Using lowercase variants like @auraenabled or @istest works in Apex (which is case-insensitive) but reduces readability and deviates from Salesforce documentation standards.

## Noncompliant code example

```apex
public class MyController {
    @auraenabled // Noncompliant — should be @AuraEnabled
    public static String getName() {
        return 'Hello';
    }

    @istest // Noncompliant — should be @IsTest
    static void testGetName() {
        System.assertEquals('Hello', MyController.getName());
    }

    @future // Noncompliant — should be @Future
    public static void doAsync() {
        // async work
    }
}
```

## Compliant solution

```apex
public class MyController {
    @AuraEnabled
    public static String getName() {
        return 'Hello';
    }

    @IsTest
    static void testGetName() {
        System.assertEquals('Hello', MyController.getName());
    }

    @Future
    public static void doAsync() {
        // async work
    }
}
```
