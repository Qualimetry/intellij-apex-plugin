public class CustomerData {
    private String socialSecurityNumber;
    public String CreditCard__c;
    public String Email__c;
    public Date BirthDate__c;
    public String DriversLicense;
    public String PassportNumber;
    public String BankAccount;
    public Integer orderCount;

    public void process() {
        System.debug(socialSecurityNumber);
    }
}
