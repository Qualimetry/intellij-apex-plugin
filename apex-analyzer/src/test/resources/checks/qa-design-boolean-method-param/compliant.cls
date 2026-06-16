public class ReportGeneratorCompliant {
    private String reportName;

    public ReportGeneratorCompliant(String reportName) {
        this.reportName = reportName;
    }

    public void generateActiveReport() {
        List<Case> cases = [SELECT Id, Subject FROM Case WHERE IsArchived__c = false];
    }

    public void generateFullReport() {
        List<Case> cases = [SELECT Id, Subject FROM Case];
    }

    public void sendReportAsync(String recipient) {
        System.enqueueJob(new SendReportJob(recipient));
    }
}
