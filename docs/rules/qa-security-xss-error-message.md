# Error messages in UI must be escaped to prevent XSS

`qa-security-xss-error-message` &middot; Security &middot; Security Hotspot &middot; severity MAJOR &middot; enabled in the recommended profile

Rendering user-controlled data in Visualforce error messages without proper escaping enables cross-site scripting (XSS) attacks. An attacker can inject JavaScript that executes in the context of the victim's browser session, stealing session tokens or performing actions on behalf of the user. Always use the default escaping behavior or explicitly escape message content.

## Noncompliant code example

```apex
public class FormController {
    public PageReference submit() {
        String userInput = ApexPages.currentPage()
            .getParameters().get('name');
        try {
            processInput(userInput);
        } catch (Exception e) {
            ApexPages.addMessage(new ApexPages.Message(
                ApexPages.Severity.ERROR, userInput, false));   // Noncompliant — escape=false
        }
        return null;
    }
}
```

## Compliant solution

```apex
public class FormController {
    public PageReference submit() {
        String userInput = ApexPages.currentPage()
            .getParameters().get('name');
        try {
            processInput(userInput);
        } catch (Exception e) {
            ApexPages.addMessage(new ApexPages.Message(
                ApexPages.Severity.ERROR,
                'Invalid input provided. Please try again.'));
        }
        return null;
    }
}
```
