public class NonCompliant {
    private Integer temp;
    private String buffer;
    private Boolean ready;

    public void process() {
        temp = 42;
        System.debug(temp);
    }

    public void format() {
        buffer = 'hello';
        System.debug(buffer);
    }

    public void enable() {
        ready = true;
    }

    public void disable() {
        ready = false;
    }
}
