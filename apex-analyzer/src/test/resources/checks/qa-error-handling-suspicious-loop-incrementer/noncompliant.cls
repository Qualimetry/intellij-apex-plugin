public class LoopProcessor {
    public void processItems(List<String> items) {
        Integer j = 0;
        for (Integer i = 0; i < items.size(); j++) {
            System.debug(items[i]);
        }
    }

    public void processRecords(List<Account> accounts) {
        Integer counter = 0;
        for (Integer k = 0; k < accounts.size(); counter++) {
            processAccount(accounts[k]);
        }
    }

    public void normalLoop(Integer limit) {
        for (Integer x = 0; x < limit; x++) {
            doWork(x);
        }
    }

    void processAccount(Account a) { }
    void doWork(Integer n) { }
}
