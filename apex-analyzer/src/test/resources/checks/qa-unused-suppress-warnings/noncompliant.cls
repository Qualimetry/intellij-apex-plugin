public class WithSuppressWarnings {
    @SuppressWarnings('PMD')
    public void complexMethod() {
        String s = 'value';
        if (s != null) {
            process(s);
        }
    }

    @SuppressWarnings('PMD.AvoidDeeplyNestedIfStmts')
    public void nestedLogic(Boolean a, Boolean b) {
        if (a) {
            if (b) {
                doWork();
            }
        }
    }
}
