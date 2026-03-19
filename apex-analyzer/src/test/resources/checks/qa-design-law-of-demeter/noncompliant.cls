public class ReportBuilder {
    public String getOwnerEmail(Account acc) {
        return acc.getOwner().getContact().getEmail();
    }

    public String getRegion(Opportunity opp) {
        return opp.getAccount().getAddress().getState();
    }

    public void logDetails(Lead lead) {
        System.debug(lead.getName());
    }
}
