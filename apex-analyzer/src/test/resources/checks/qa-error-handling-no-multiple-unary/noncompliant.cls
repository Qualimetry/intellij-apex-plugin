public class NoMultipleUnaryNoncompliant {
    public void doubleNegation(Boolean flag) {
        Boolean b = !!flag;
    }

    public void conditionalDoubleNot(Boolean active) {
        if (!!active) {
            System.debug('value');
        }
    }

    public void embeddedIncrement(Integer x) {
        Integer y = ++x + 1;
    }

    public void embeddedDecrement(Integer a) {
        Integer b = --a + 5;
    }
}
