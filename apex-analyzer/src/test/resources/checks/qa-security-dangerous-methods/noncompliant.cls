public class AdminToolsController {
    public String scriptInput { get; set; }

    public void runScript() {
        ToolingAPI tooling = new ToolingAPI();
        tooling.executeAnonymous(scriptInput);
    }

    public void convertRecord(Id leadId) {
        Database.LeadConvert lc = new Database.LeadConvert();
        lc.setLeadId(leadId);
    }
}
