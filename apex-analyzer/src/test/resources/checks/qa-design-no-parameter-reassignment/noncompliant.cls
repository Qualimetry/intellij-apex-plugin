public class StringProcessor {
    public String process(String input, Integer count) {
        input = input.trim();
        return input;
    }

    public Integer clamp(Integer value, Integer maxVal) {
        if (value > maxVal) {
            value = maxVal;
        }
        return value;
    }
}
