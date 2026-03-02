public class MathHelper {
    private Integer unusedField;

    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    public String formatCurrency(Decimal amount) {
        return String.valueOf(amount);
    }

    public void useInstance() {
        unusedField = 42;
    }
}
