# Every Apex class should have a corresponding test class

`qa-testing-class-requires-test` &middot; Testing &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Every Apex class should have a corresponding test class to ensure code correctness, catch regressions, and meet the Salesforce minimum 75% code coverage requirement for deployment. Classes without tests are a deployment risk and reduce confidence in code changes.

## Noncompliant code example

```apex
// AccountService.cls exists but AccountServiceTest.cls does NOT exist
public class AccountService {               // Noncompliant — no test class
    public void processAccounts() {
        List<Account> accounts = [SELECT Id FROM Account LIMIT 100];
        for (Account a : accounts) {
            a.Description = 'Processed';
        }
        update accounts;
    }
}
```

## Compliant solution

```apex
// AccountServiceTest.cls
@IsTest
private class AccountServiceTest {
    @IsTest
    static void testProcessAccounts() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        Test.startTest();
        new AccountService().processAccounts();
        Test.stopTest();
        Account result = [SELECT Description FROM Account WHERE Id = :acc.Id];
        Assert.areEqual('Processed', result.Description);
    }
}
```
