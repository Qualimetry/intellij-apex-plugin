public class Compliant {
    private Integer shared;
    private String label;
    private Boolean active;

    public void init() {
        shared = 10;
        label = 'default';
    }

    public void process() {
        System.debug(shared);
        System.debug(label);
    }

    public void activate() {
        active = true;
    }

    public void deactivate() {
        active = false;
    }
}
