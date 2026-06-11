public class ComparisonHelper {

    private Integer threshold;

    public ComparisonHelper(Integer threshold) {
        this.threshold = threshold;
    }

    public Boolean isAboveThreshold(Integer a, Integer b) {
        if (a == b) {
            return true;
        }
        return false;
    }

    public Integer getThreshold() {
        return threshold;
    }
}
