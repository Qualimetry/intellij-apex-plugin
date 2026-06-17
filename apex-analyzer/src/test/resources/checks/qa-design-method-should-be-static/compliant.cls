public class MathHelper {
    private Integer unusedField;

    public static Integer add(Integer a, Integer b) {
        return a + b;
    }

    public static String formatCurrency(Decimal amount) {
        return String.valueOf(amount);
    }

    public void useInstance() {
        unusedField = 42;
    }
}
