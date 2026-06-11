public with sharing class AccountReportService {

    private String accountType;

    public AccountReportService(String accountType) {
        this.accountType = accountType;
    }

    public Integer getActiveAccountCount() {
        Integer count = [SELECT Id FROM Account WHERE Type = :accountType].size();
        return count;
    }

    public Boolean hasAccounts() {
        return [SELECT COUNT() FROM Account] > 0;
    }
}
