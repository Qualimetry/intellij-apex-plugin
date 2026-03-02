public class Complex {
    public String evaluate(Claim__c claim) {
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
        while (hasMore()) {
            process();
        }
        switch on claim.Priority__c {
            when 'High' { return 'Urgent'; }
            when 'Low' { return 'Normal'; }
        }
        try {
            validate();
        } catch (Exception e) {
            return 'Error';
        }
        return 'Pending';
    }

    private Boolean hasMore() { return false; }
    private void process() { }
    private void validate() { }
}
