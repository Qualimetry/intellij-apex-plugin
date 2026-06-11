public class StrayStatements {
    private String name;
    ;

    public void processAccounts(List<Account> accounts) {
        for (Account acc : accounts) {
            acc.Name = 'Updated';
        }
        ;
        update accounts;
    }

    public Integer calculate(Integer a, Integer b) {
        Integer result = a + b;
        return result;
    }

    ;
}
