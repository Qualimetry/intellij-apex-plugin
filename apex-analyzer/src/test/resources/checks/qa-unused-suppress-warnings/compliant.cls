public class NoSuppressWarnings {
    public void cleanMethod() {
        String s = 'value';
        if (String.isNotBlank(s)) {
            process(s);
        }
    }

    public void flatLogic(Boolean a, Boolean b) {
        if (a && b) {
            doWork();
        }
    }
}
