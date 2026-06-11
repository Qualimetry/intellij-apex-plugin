public with sharing class AccountValidator {

    private Boolean strictMode;

    public AccountValidator(Boolean strict) {
        this.strictMode = strict;
    }

    public void validate(Account acc) {
        if (acc != null && strictMode) {
            acc.Description = Label.SomeLabel;
        }
    }

    public Boolean isStrictMode() {
        return strictMode;
    }
}
