public class TypeConversionService {

    private Integer recordCount;
    private Decimal totalAmount;

    public String getCountLabel() {
        String label = "" + recordCount;
        return label;
    }

    public void logStatus(Boolean isActive) {
        System.debug('Status: ' + String.valueOf(isActive));
    }
}
