public class VariableNamingNonCompliant {

    private Integer validCount;

    public void process(String input) {
        String result = input.toUpperCase();
        validCount = result.length();
    }

    void run() {
        Integer My_Var = 1;
        String properName = 'hello';
        System.debug(properName + My_Var);
    }
}
