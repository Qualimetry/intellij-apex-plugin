public class UnusedPrivateNonCompliant {

    private String name;

    public void run() {
        name = 'test';
        execute();
    }

    private void execute() {
        System.debug(name);
    }

    private void helper() { }
}
