# Dynamic SOQL must escape all user-supplied input

`qa-security-escape-dynamic-soql` &middot; Security &middot; Security Hotspot &middot; severity MAJOR &middot; enabled in the recommended profile

Concatenating user-supplied input directly into a dynamic SOQL string creates a SOQL injection vulnerability. An attacker can manipulate the query to read unauthorized data, bypass record-level security, or extract sensitive fields. Always escape user input with String.escapeSingleQuotes() or use bind variables in inline SOQL queries.

## Noncompliant code example

```apex
public class AccountSearchController {
    public List<Account> search(String userInput) {
        String query = 'SELECT Id, Name, Phone FROM Account ' +
            'WHERE Name LIKE \'%' + userInput + '%\'';   // Noncompliant
        return Database.query(query);
    }
}
```

## Compliant solution

```apex
public class AccountSearchController {
    public List<Account> search(String userInput) {
        String safe = '%' + String.escapeSingleQuotes(userInput) + '%';
        String query = 'SELECT Id, Name, Phone FROM Account ' +
            'WHERE Name LIKE \'' + safe + '\'';
        return Database.query(query);
    }
}
```
