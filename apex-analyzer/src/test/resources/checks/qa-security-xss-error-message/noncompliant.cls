public class UnsafeMessage {
    public void showError(String userInput) {
        ApexPages.addMessage(new ApexPages.Message(ApexPages.Severity.ERROR, userInput, false));
    }

    public void displayValidation(String field) {
        String msg = 'Invalid value for: ' + field;
        ApexPages.addMessage(new ApexPages.Message(ApexPages.Severity.WARNING, msg));
    }

    public void safeMessage() {
        ApexPages.addMessage(new ApexPages.Message(ApexPages.Severity.INFO, 'All good'));
    }
}
