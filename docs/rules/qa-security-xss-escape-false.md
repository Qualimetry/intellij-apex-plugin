# addError with escape disabled is vulnerable to XSS

`qa-security-xss-escape-false` &middot; Security &middot; Security Hotspot &middot; severity CRITICAL &middot; enabled in the recommended profile

This rule flags calls to addError(message, false) where the second parameter disables HTML escaping. When escape is set to false, any user-controlled or unsanitized content in the error message is rendered as raw HTML, creating a cross-site scripting (XSS) vulnerability. Always use the default escaping behavior or explicitly sanitize the message before disabling escape.

## Noncompliant code example

```apex
trigger ContactValidation on Contact (before insert) {
    for (Contact c : Trigger.new) {
        if (String.isBlank(c.Email)) {
            String msg = 'Missing email for: ' + c.LastName;
            c.addError(msg, false); // Noncompliant — XSS via LastName
        }
        if (c.Phone == null) {
            c.Phone.addError('Phone is required: ' + c.LastName, false); // Noncompliant
        }
    }
}
```

## Compliant solution

```apex
trigger ContactValidation on Contact (before insert) {
    for (Contact c : Trigger.new) {
        if (String.isBlank(c.Email)) {
            c.addError('Missing email for this contact.');
        }
        if (c.Phone == null) {
            c.Phone.addError('Phone is required.');
        }
    }
}
```
