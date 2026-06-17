public class SwitchCaseNonCompliant {
    private String result;

    public void categorize(Integer code) {
        switch on code {
            when 1 { result = 'A'; }
            when 2 { result = 'B'; }
            when 3 { result = 'C'; }
            when 4 { result = 'D'; }
            when 5 { result = 'E'; }
            when 6 { result = 'F'; }
            when 7 { result = 'G'; }
            when 8 { result = 'H'; }
            when 9 { result = 'I'; }
            when 10 { result = 'J'; }
            when 11 { result = 'K'; }
        }
    }
}
