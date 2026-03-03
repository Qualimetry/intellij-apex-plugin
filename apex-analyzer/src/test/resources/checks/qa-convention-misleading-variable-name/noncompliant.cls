public class AccountProcessor {
    public void process(List<Account> accounts) {
        Object data = accounts;
        for (Account acc : accounts) {
            acc.Description = 'processed';
        }
        String temp = 'value';
        System.debug(temp);
    }

    public void validate() {
        Boolean isValid = true;
    }
}
