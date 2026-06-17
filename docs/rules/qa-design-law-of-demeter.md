# Limit chained method calls to reduce coupling

`qa-design-law-of-demeter` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

The Law of Demeter (principle of least knowledge) states that a method should only call methods on objects it directly owns. Deeply chained method calls like a.getB().getC().doWork() create tight coupling between classes, making the code fragile and difficult to refactor when any intermediate class changes its API.

## Noncompliant code example

```apex
public class ReportBuilder {
    public String getOwnerEmail(Account acc) {
        return acc.getOwner().getContact().getEmail().toLowerCase(); // Noncompliant - deep chain
    }

    public String getRegion(Opportunity opp) {
        return opp.getAccount().getAddress().getState().toUpperCase(); // Noncompliant - deep chain
    }
}
```

## Compliant solution

```apex
public class ReportBuilder {
    public String getOwnerEmail(Account acc) {
        User owner = acc.getOwner();
        String email = owner.Email;
        return email.toLowerCase();
    }

    public String getRegion(Opportunity opp) {
        Account acct = opp.getAccount();
        return acct.BillingState;
    }
}
```
