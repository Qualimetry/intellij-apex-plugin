public class SafeMessage {
    public void showError(String userInput) {
        ApexPages.addMessage(
            new ApexPages.Message(ApexPages.Severity.ERROR, userInput));
    }

    public void displayValidation(String field) {
        String msg = 'Invalid value for: ' + field.escapeHtml4();
        ApexPages.addMessage(
            new ApexPages.Message(ApexPages.Severity.WARNING, msg));
    }
}
