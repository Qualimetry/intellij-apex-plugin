public class FewReturns {
    private static final Map<Integer, String> CATEGORIES = new Map<Integer, String>{
        90 => 'Excellent', 80 => 'Good', 70 => 'Average',
        60 => 'Below Average', 50 => 'Poor'
    };

    public String categorize(Integer score) {
        for (Integer threshold : CATEGORIES.keySet()) {
            if (score >= threshold) {
                return CATEGORIES.get(threshold);
            }
        }
        return 'None';
    }
}
