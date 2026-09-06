public class RecordIterator {
    public void processForward(List<Account> accounts) {
        Integer i = 0;
        for (; i < accounts.size(); i++) {
            System.debug(accounts[i].Name);
        }
    }

    public void processBackward(List<Account> accounts) {
        for (Integer j = accounts.size() - 1; j >= 0;) {
            System.debug(accounts[j].Name);
            j--;
        }
    }
}
