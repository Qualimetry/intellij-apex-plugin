public class ReportGenerator {
    private String reportName;

    public ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    public void generateReport(Boolean includeArchived) {
        List<Case> cases;
        if (includeArchived) {
            cases = [SELECT Id, Subject FROM Case];
        } else {
            cases = [SELECT Id, Subject FROM Case WHERE IsArchived__c = false];
        }
    }

    public void sendReport(String recipient, Boolean async) {
        if (async) {
            System.enqueueJob(new SendReportJob(recipient));
        }
    }
}
