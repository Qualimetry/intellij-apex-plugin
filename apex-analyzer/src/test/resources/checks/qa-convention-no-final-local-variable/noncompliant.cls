public class InvoiceCalculator {
    public Decimal calculateTotal(List<LineItem> items) {
        final Decimal taxRate = 0.08;
        final Decimal subtotal = 0;

        for (LineItem item : items) {
            subtotal += item.amount;
        }

        return subtotal * (1 + taxRate);
    }
}
