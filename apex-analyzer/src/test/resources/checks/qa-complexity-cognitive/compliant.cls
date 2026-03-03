public class Ctr {
    public void evaluate(Record__c rec) {
        if (rec.Status__c != 'Active') return;
        for (Rule__c rule : getRules()) {
            if (!rule.IsEnabled__c) continue;
            evaluateRule(rec, rule);
        }
    }

    private void evaluateRule(Record__c rec, Rule__c rule) {
        if (rule.Type__c == 'Validation') {
            applyIfEligible(rec, rule);
        } else if (rule.Type__c == 'Transform') {
            safeTransform(rec, rule);
        }
    }

    private void applyIfEligible(Record__c rec, Rule__c rule) { }
    private void safeTransform(Record__c rec, Rule__c rule) { }
    private List<Rule__c> getRules() { return new List<Rule__c>(); }
}
