public class ErrorHandler {
    public void processRecords() {
        try {
            insert new Account(Name = 'Test');
        } catch (DmlException ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    public void handleCallout() {
        try {
            Http h = new Http();
            HttpResponse res = h.send(new HttpRequest());
        } catch (CalloutException ce) {
            throw new ServiceException(ce.getMessage());
        }
    }

    public void safeBatch() {
        try {
            Database.executeBatch(new MyBatch());
        } catch (AsyncException ae) {
            throw ae;
        }
    }
}
