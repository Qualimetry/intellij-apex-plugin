public class StringProcessor {
    public String normalize(String input) {
        String normalized = input.trim();
        return normalized;
    }

    public Integer clampValue(Integer value, Integer maxVal) {
        Integer result = value;
        if (result > maxVal) {
            result = maxVal;
        }
        return result;
    }
}
