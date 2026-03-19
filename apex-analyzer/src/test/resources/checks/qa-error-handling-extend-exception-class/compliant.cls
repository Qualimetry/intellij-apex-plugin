public class OrderException extends Exception {
    private String orderId;
    private String errorCode;

    public OrderException(String msg) {
        this.orderId = null;
        this.errorCode = 'UNKNOWN';
    }

    public OrderException(String msg, String orderId, String code) {
        this.orderId = orderId;
        this.errorCode = code;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
