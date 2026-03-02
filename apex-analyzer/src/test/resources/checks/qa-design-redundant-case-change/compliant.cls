public class Compliant {
    public String normalize(String s) {
        return s.toLowerCase();
    }

    public String upper(String s) {
        return s.toUpperCase();
    }

    public String transform(String a, String b) {
        return a.toLowerCase() + b.toUpperCase();
    }

    public Boolean compare(String x, String y) {
        return x.toLowerCase() == y.toLowerCase();
    }
}
