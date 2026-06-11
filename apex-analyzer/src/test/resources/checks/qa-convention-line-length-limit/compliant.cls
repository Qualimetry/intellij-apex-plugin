public class LineLengthCompliant {
    private String name;
    private Integer count;

    public void process() {
        name = 'Short Line';
        count = 42;
    }

    public String getInfo() {
        return name + ': ' + count;
    }
}
