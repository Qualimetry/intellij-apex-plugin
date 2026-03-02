public class ComparisonHelper {

    private Integer threshold;

    public ComparisonHelper(Integer threshold) {
        this.threshold = threshold;
    }

    public Boolean isAlwaysTrue(Integer a) {
        if (a == a) {
            return true;
        }
        return false;
    }

    public Integer getThreshold() {
        return threshold;
    }
}
