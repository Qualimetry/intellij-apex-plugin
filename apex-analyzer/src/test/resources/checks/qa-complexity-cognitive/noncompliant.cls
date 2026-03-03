public class Ctr {
    public void evaluate(Record__c rec) {
        if (rec.Status__c == 'Active') {
            for (Rule__c rule : getRules()) {
                if (rule.IsEnabled__c) {
                    if (rule.Type__c == 'Validation') {
                        if (rec.Amount__c > rule.Threshold__c && rec.Region__c != null) {
                            applyRule(rec, rule);
                        }
                    } else if (rule.Type__c == 'Transform') {
                        try {
                            transform(rec, rule);
                        } catch (Exception e) {
                            if (rule.Required__c) {
                                throw e;
                            }
                        }
                    }
                }
            }
        }
    }

    private List<Rule__c> getRules() { return new List<Rule__c>(); }
    private void applyRule(Record__c rec, Rule__c rule) { }
    private void transform(Record__c rec, Rule__c rule) { }
}
