public with sharing class DataService {

    private String serviceName;

    public DataService(String name) {
        this.serviceName = name;
    }

    public void executeQuery() {
        try {
            List<Account> accounts = [SELECT Id FROM Account];
            processResults(accounts);
        } catch (QueryException e) {
            System.debug(e.getMessage());
        }
    }

    private void processResults(List<Account> accounts) {
        for (Account a : accounts) {
            a.Description = serviceName;
        }
    }
}
