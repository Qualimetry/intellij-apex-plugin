public class OrderService {
    private String status;
    private Integer count;
    public void process() {
        validate();
        execute();
    }
    private void validate() { }
    private void execute() { }
    public String getStatus() { return status; }
    public Integer getCount() { return count; }
}
