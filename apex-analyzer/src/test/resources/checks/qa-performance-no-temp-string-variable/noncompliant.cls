public class TempStringVar {
    public String convertId(Id recordId) {
        String s = String.valueOf(recordId);
        return s;
    }

    public String formatAmount(Decimal amount) {
        String formatted = String.valueOf(amount);
        return formatted;
    }

    public String getLabel(Schema.SObjectField field) {
        String label = field.getDescribe().getLabel();
        return label;
    }
}
