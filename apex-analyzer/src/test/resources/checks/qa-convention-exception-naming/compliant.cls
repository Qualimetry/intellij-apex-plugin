public class OrderProcessingException extends Exception {
    private String orderId;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
