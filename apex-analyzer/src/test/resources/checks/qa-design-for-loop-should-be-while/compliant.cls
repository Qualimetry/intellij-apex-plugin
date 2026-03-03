public class RecordIterator {
    public void processForward(List<Account> accounts) {
        Integer i = 0;
        while (i < accounts.size()) {
            System.debug(accounts[i].Name);
            i++;
        }
    }

    public void processBackward(List<Account> accounts) {
        Integer j = accounts.size() - 1;
        while (j >= 0) {
            System.debug(accounts[j].Name);
            j--;
        }
    }
}
