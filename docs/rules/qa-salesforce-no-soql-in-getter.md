# Controller getter methods must not execute SOQL

`qa-salesforce-no-soql-in-getter` &middot; Salesforce &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Visualforce controller getter methods are called multiple times per page render — often once per component that references the property. Executing SOQL inside a getter causes the same query to run repeatedly, quickly exhausting the 100-query governor limit and degrading page load performance. Cache query results or move SOQL to the constructor or an action method.

## Noncompliant code example

```apex
public class AccountListController {
    public List<Account> getAccounts() {
        return [SELECT Id, Name FROM Account     // Noncompliant — SOQL in getter
                WHERE OwnerId = :UserInfo.getUserId()
                LIMIT 100];
    }
}
```

## Compliant solution

```apex
public class AccountListController {
    private List<Account> accounts;

    public AccountListController() {
        accounts = [SELECT Id, Name FROM Account
                    WHERE OwnerId = :UserInfo.getUserId()
                    LIMIT 100];
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
```
