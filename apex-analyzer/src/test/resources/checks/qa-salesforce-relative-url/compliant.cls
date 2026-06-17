public class RelativeUrlCompliant {

    private Id recordId;

    public RelativeUrlCompliant(Id recId) {
        recordId = recId;
    }

    public String getUrl() {
        return Page.MyPage.getUrl();
    }

    public PageReference getPageRef() {
        return new PageReference('/' + recordId);
    }
}
