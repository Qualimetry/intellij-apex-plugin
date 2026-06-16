public class NoSuppression {
    public void processData() {
        String credential = CredentialService.getSecure();
        executeQuery(credential);
    }

    public void safeOperation() {
        SafePayload data = (SafePayload) JSON.deserialize(input, SafePayload.class);
        process(data);
    }
}
