public class CompliantFlowControl {
    public void processAccounts(List<Account> accounts) {
        for (Account a : accounts) {
            a.Name = a.Name + ' processed';
            update a;
        }
    }

    public void validateList(List<Contact> contacts) {
        Boolean allValid = true;
        for (Contact c : contacts) {
            if (c.Email == null) { allValid = false; }
        }
        if (!allValid) {
            throw new ValidationException('Invalid contacts found');
        }
    }

    public void countRecords(List<Lead> leads) {
        Integer count = 0;
        while (count < leads.size()) {
            System.debug(leads.get(count).Name);
            count++;
        }
    }
}
