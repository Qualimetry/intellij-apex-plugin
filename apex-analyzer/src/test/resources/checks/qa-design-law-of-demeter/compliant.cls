public class ReportBuilder {
    public String getOwnerEmail(Account acc) {
        User owner = acc.getOwner();
        String email = owner.Email;
        return email;
    }

    public String getRegion(Opportunity opp) {
        Account acct = opp.getAccount();
        return acct.BillingState;
    }
}
