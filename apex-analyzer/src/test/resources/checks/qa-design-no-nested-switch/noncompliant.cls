public class Ctr {
    public void run(Integer x, Integer y) {
        switch (x) {
            case 1:
                switch (y) {
                    case 1: doWork(); break;
                }
                break;
        }
    }
    void doWork() { }
}
