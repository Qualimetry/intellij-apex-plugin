public class SafeToolsController {
    public String scriptInput { get; set; }

    public void processRecords() {
        List<Account> accounts = [SELECT Id, Name FROM Account LIMIT 10];
        for (Account a : accounts) {
            a.Description = 'Reviewed';
        }
        update accounts;
    }

    public void validateInput(String input) {
        if (String.isBlank(input)) {
            throw new IllegalArgumentException('Input cannot be blank');
        }
    }
}
