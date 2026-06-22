public class WithNosonar {
    public void processData() {
        String password = getPassword(); //NOSONAR
        executeQuery(password);
    }

    public void riskyOperation() {
        Object data = JSON.deserializeUntyped(input); //NOSONAR
        process(data);
    }
}
