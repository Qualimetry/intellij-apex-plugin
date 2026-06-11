public class DenseSwitch {
  void run(String x) {
    switch on x {
      when 'a' {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 4;
        Integer e = 5;
        Integer f = 6;
      }
      when 'b' { doB(); }
    }
  }
  private void doB() {}
}
