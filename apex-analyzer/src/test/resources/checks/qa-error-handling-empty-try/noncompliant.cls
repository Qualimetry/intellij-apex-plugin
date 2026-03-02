public class NonCompliantTryHandler {
    public void processRecords() {
        try { } catch (DmlException e) { System.debug(e); }
    }

    public void callExternalService() {
        String result = 'default';
        try { } catch (CalloutException e) { result = 'error'; }
        System.debug(result);
    }

    public void batchProcess(List<Account> accounts) {
        for (Account a : accounts) {
            a.Name = a.Name + ' Updated';
        }
        try { } finally { System.debug('done'); }
    }
}
