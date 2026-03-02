public class Simple {
    public String evaluate(Claim__c claim) {
        if (claim.Amount__c <= 10000) return 'Pending';
        if (claim.Type__c == 'Dental') return evaluateDental(claim);
        if (claim.Type__c == 'Medical') return evaluateMedical(claim);
        return 'Pending';
    }

    private String evaluateDental(Claim__c claim) {
        return claim.Amount__c < 5000 ? 'Auto-Approved' : 'Review';
    }

    private String evaluateMedical(Claim__c claim) {
        if (claim.Status__c != 'Open') return 'Pending';
        return hasVerifiedDocuments(claim) ? 'Auto-Approved' : 'Pending';
    }

    private Boolean hasVerifiedDocuments(Claim__c claim) {
        return false;
    }
}
