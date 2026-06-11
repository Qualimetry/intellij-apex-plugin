public class UnusedPrivateCompliant {

    private String name;

    public void run() {
        name = 'test';
        execute();
        helper();
    }

    private void execute() {
        System.debug(name);
    }

    private void helper() {
        System.debug('Helper called');
    }
}
