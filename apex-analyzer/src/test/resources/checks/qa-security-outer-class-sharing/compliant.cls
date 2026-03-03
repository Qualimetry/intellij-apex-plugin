public with sharing class OuterSharingCompliant {
    private List<Account> accounts;

    public void loadAccounts() {
        accounts = [SELECT Id, Name FROM Account];
    }

    public Integer getAccountCount() {
        return accounts.size();
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
