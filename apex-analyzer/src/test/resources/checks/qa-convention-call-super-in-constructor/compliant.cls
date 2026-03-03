public class Child extends Parent {
    private String name;

    public Child() {
        super();
        this.name = 'default';
    }

    public Child(String n) {
        super();
        this.name = n;
    }

    public void process() { }
}
