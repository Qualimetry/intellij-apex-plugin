// Manages order lifecycle operations.
public class OrderService {
    private String status;
    private Integer count;

    // Validates and executes the order processing pipeline.
    public void process() {
        validate();
        execute();
    }

    // Internal validation step.
    private void validate() { }
    private void execute() { }
    public String getStatus() { return status; }
}
