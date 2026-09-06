# Dangerous methods should not be called

`qa-security-dangerous-methods` &middot; Security &middot; Security Hotspot &middot; severity CRITICAL &middot; enabled in the recommended profile

This rule detects invocations of Apex methods that carry significant security risk when called with unvalidated input. Methods such as executeAnonymous(), compileAndRun(), merge(), and convertLead() can execute arbitrary code, alter data irreversibly, or bypass security controls. These methods should only be used in controlled contexts with validated input.

## Noncompliant code example

```apex
public class AdminToolsController {
    public String scriptInput { get; set; }

    public void runScript() {
        ToolingApi t = new ToolingApi();
        t.executeAnonymous(scriptInput); // Noncompliant
    }

    public void mergeRecords(Id masterId, Id duplicateId) {
        Account master = [SELECT Id FROM Account WHERE Id = :masterId];
        Account dup = [SELECT Id FROM Account WHERE Id = :duplicateId];
        merge master dup; // Noncompliant — irreversible data change
    }
}
```

## Compliant solution

```apex
public class AdminToolsController {
    public void mergeRecords(Id masterId, Id duplicateId) {
        if (!FeatureManagement.checkPermission('Allow_Merge')) {
            throw new SecurityException('Insufficient permissions');
        }
        // Validate inputs, log audit trail, then proceed
    }
}
```
