public class SwitchCaseCompliant {
    private String result;

    public void categorize(Integer code) {
        switch on code {
            when 1 {
                result = 'One';
            }
            when 2 {
                result = 'Two';
            }
            when else {
                result = 'Other';
            }
        }
    }
}
