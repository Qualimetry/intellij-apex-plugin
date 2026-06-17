# Method cyclomatic complexity must not exceed threshold

`qa-complexity-cyclomatic` &middot; Complexity &middot; Code Smell &middot; severity CRITICAL &middot; enabled in the recommended profile

Cyclomatic complexity measures the number of independent execution paths through a method. Each if, for, while, catch, switch case, and logical operator (&&, ||) adds a path. High complexity makes methods hard to test (each path needs a test case) and understand. Extract complex logic into helper methods to reduce the path count.

## Noncompliant code example

```apex
public class ClaimProcessor {
    public String evaluate(Claim__c claim) {       // Noncompliant — complexity > 10
        if (claim.Amount__c > 10000) {
            if (claim.Type__c == 'Medical') {
                if (claim.Status__c == 'Open') {
                    for (Document__c doc : claim.Documents__r) {
                        if (doc.Verified__c) {
                            if (doc.Type__c == 'Receipt' || doc.Type__c == 'Invoice') {
                                return 'Auto-Approved';
                            }
                        }
                    }
                }
            } else if (claim.Type__c == 'Dental') {
                return claim.Amount__c < 5000 ? 'Auto-Approved' : 'Review';
            }
        }
        return 'Pending';
    }
}
```

## Compliant solution

```apex
public class ClaimProcessor {
    public String evaluate(Claim__c claim) {
        if (claim.Amount__c <= 10000) return 'Pending';
        if (claim.Type__c == 'Dental') return evaluateDental(claim);
        if (claim.Type__c == 'Medical') return evaluateMedical(claim);
        return 'Pending';
    }

    private String evaluateMedical(Claim__c claim) {
        if (claim.Status__c != 'Open') return 'Pending';
        return hasVerifiedDocuments(claim) ? 'Auto-Approved' : 'Pending';
    }
}
```
