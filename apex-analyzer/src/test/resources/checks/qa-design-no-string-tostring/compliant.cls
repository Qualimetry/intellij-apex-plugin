public class Compliant {
    public String fromInteger(Integer n) {
        return String.valueOf(n);
    }

    public String fromObject(Object obj) {
        return obj.toString();
    }

    public String concatIds(Integer a, Integer b) {
        String result = a.toString() + b.toString();
        return result;
    }

    public String getLabel() {
        return 'Hello World';
    }

    public String formatNumber(Decimal d) {
        return d.format();
    }
}
