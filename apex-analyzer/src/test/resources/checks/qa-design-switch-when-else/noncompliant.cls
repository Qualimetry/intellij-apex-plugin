public class SwitchWhenElseNonCompliant {
    private String result;

    public void categorize(Integer code) {
        switch on code {
            when 1 {
                result = 'One';
            }
            when 2 {
                result = 'Two';
            }
        }
    }

    public String getResult() {
        return result;
    }
}
