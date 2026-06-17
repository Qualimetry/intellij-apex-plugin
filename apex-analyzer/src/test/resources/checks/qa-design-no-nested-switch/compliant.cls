public class Ctr {
    public void run(Integer x) {
        switch (x) {
            case 1: doA(); break;
            case 2: doB(); break;
        }
    }
    void doA() { }
    void doB() { }
}
