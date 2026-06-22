public class Compliant {
    public void handleStatus(String status) {
        switch (status) {
            when 'Active' {
                activate();
            }
            when 'Inactive' {
                deactivate();
            }
            when else {
                handleDefault();
            }
        }
    }

    public void process(Integer code) {
        if (code == 1) {
            doA();
        } else if (code == 2) {
            doB();
        }
    }
}
