public class LineLengthNonCompliant {
    private String name;
    private Integer count;

    public void process() {
        name = 'Short Line';
        count = 42;
    }

    private static final String DESCRIPTION = 'This line is intentionally made way over the maximum allowed character limit of one hundred and twenty characters and should trigger the rule';

    public String getInfo() {
        return name;
    }
}
