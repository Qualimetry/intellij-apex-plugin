public class TypeConversionService {

    private Integer recordCount;
    private Decimal totalAmount;

    public String getCountLabel() {
        String label = String.valueOf(recordCount);
        return label;
    }

    public String formatAmount() {
        return String.valueOf(totalAmount);
    }
}
