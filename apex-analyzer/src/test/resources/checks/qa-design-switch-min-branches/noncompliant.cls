public class NonCompliant {
    public void handleType(Integer t) {
        switch (t) {
            when 1 {
                doFirst();
            }
            when 2 {
                doSecond();
            }
        }
    }

    public void process(String action) {
        Boolean valid = true;
        switch (action) {
            when 'run' {
                execute();
            }
        }
    }
}
