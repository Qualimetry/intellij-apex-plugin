public class NonCompliant {
    public String wrap(String s) {
        return new String(s);
    }

    public void process() {
        String name = new String('Alice');
        String copy = new String(name);
        System.debug(name + copy);
    }

    public String getDefault() {
        return new String('default');
    }
}
