public class LeadConverter {

    public Account convertLead(Lead ld) {
        Account acc = new Account(Name = ld.Company);
        System.debug('Converting: ' + acc.Name);
        return acc;
    }

    public String getFullName(Contact c) {
        return c.FirstName + ' ' + c.LastName;
    }

    public Integer calculateScore(Opportunity opp) {
        Integer score = opp.Amount != null ? 100 : 0;
        score += opp.StageName == 'Closed Won' ? 50 : 0;
        return score;
    }

    public List<String> getNames(List<Account> accounts) {
        List<String> names = new List<String>();
        for (Account a : accounts) {
            names.add(a.Name);
        }
        return names;
    }
}
