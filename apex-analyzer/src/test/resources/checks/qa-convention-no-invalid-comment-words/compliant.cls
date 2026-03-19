public class OrderService {
    public void validate() {
        validateInput();
        validateRules();
    }

    public void process() {
        handleEdgeCases();
        executeLogic();
    }

    public void submit() {
        send();
    }
}
