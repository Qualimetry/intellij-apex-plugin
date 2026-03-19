public class ManualBlankCheck {
    public Boolean isValid(String name) {
        if (name == null || name == '') {
            return false;
        }
        return true;
    }

    public void process(String input) {
        if (input != null && input != '' && input.trim() != '') {
            doWork(input);
        }
    }
}
