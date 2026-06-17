public class BadController extends BaseController {
    public List<Account> accounts;

    public BadController() {
        Account defaultAcct = new Account(Name = 'Default');
        insert defaultAcct;
        accounts = [SELECT Id, Name FROM Account LIMIT 10];
    }

    public BadController(ApexPages.StandardController ctrl) {
        insert new Contact(LastName = 'Init', AccountId = ctrl.getId());
    }
}
