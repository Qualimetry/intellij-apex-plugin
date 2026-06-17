public with sharing class AccountValidator {

    private Boolean strictMode;

    public AccountValidator(Boolean strict) {
        this.strictMode = strict;
    }

    public void validate(Account acc) {
        if (acc != null && strictMode) {
            acc.addError("Invalid value");
        }
    }

    public Boolean isStrictMode() {
        return strictMode;
    }
}
