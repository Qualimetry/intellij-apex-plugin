public class NonCompliantWhileHandler {
    public void busyWait(Boolean flag) {
        while (flag) { }
    }

    public void processRecords(List<Account> accounts) {
        Integer i = 0;
        while (i < accounts.size()) {
            accounts.get(i).Name = 'Updated';
            i++;
        }
    }

    public void spinLoop(Integer counter) {
        while (counter > 0) { }
    }
}
