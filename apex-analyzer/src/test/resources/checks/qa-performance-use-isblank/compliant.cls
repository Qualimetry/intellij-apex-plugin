public class IsBlankCheck {
    public Boolean isValid(String name) {
        if (String.isBlank(name)) {
            return false;
        }
        return true;
    }

    public void process(String input) {
        if (String.isNotBlank(input)) {
            doWork(input);
        }
    }
}
