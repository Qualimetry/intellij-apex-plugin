# Field-level security checks before data access

`qa-security-field-level-security` &middot; Security &middot; Security Hotspot &middot; severity MAJOR &middot; enabled in the recommended profile

Accessing SObject fields without verifying Field-Level Security (FLS) can expose data that the current user is not authorized to see. In Salesforce, FLS is not enforced automatically in Apex — the developer must explicitly check isAccessible() before reading and isUpdateable() before writing. Failing to do so can lead to unauthorized data disclosure, especially in managed packages and communities.

## Noncompliant code example

```apex
public class EmployeeService {
    public List<Employee__c> getEmployees() {
        return [SELECT Id, Name, Salary__c, SSN__c
                FROM Employee__c];   // Noncompliant — no FLS check on Salary__c, SSN__c
    }

    public void updateSalary(Id empId, Decimal newSalary) {
        update new Employee__c(Id = empId, Salary__c = newSalary);  // Noncompliant
    }
}
```

## Compliant solution

```apex
public class EmployeeService {
    public List<Employee__c> getEmployees() {
        if (!Schema.SObjectType.Employee__c.fields.Salary__c.isAccessible() ||
            !Schema.SObjectType.Employee__c.fields.SSN__c.isAccessible()) {
            throw new SecurityException('Insufficient field access');
        }
        return [SELECT Id, Name, Salary__c, SSN__c FROM Employee__c];
    }

    public void updateSalary(Id empId, Decimal newSalary) {
        if (!Schema.SObjectType.Employee__c.fields.Salary__c.isUpdateable()) {
            throw new SecurityException('Cannot update Salary');
        }
        update new Employee__c(Id = empId, Salary__c = newSalary);
    }
}
```
