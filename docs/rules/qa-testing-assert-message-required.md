# Assertion calls should include a descriptive message

`qa-testing-assert-message-required` &middot; Testing &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Assertion calls without a descriptive failure message make it difficult to diagnose test failures. When multiple assertions exist in a test method, a bare Assert.isTrue(condition) failure gives no context about what was being verified. Always include a message parameter that explains the expected behavior.

## Noncompliant code example

```apex
@IsTest
private class ContactServiceTest {
    @IsTest
    static void testCreateContact() {
        Contact c = ContactService.create('Smith');
        Assert.isNotNull(c.Id);                             // Noncompliant — no message
        Assert.areEqual('Smith', c.LastName);                // Noncompliant
        Assert.isTrue(c.IsActive__c);                       // Noncompliant
    }
}
```

## Compliant solution

```apex
@IsTest
private class ContactServiceTest {
    @IsTest
    static void testCreateContact() {
        Contact c = ContactService.create('Smith');
        Assert.isNotNull(c.Id, 'Contact should be inserted');
        Assert.areEqual('Smith', c.LastName, 'Last name should be set');
        Assert.isTrue(c.IsActive__c, 'Contact should be active by default');
    }
}
```
