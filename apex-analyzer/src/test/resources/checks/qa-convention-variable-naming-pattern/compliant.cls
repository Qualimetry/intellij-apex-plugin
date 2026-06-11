public class VariableNamingCompliant {

    private Integer validCount;

    public void process(String input) {
        String result = input.toUpperCase();
        validCount = result.length();
    }

    void run() {
        Integer myVar = 1;
        String properName = 'hello';
        System.debug(properName + myVar);
    }
}
