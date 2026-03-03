public class RedirectControllerCompliant {
    public String userInput { get; set; }

    public PageReference redirect() {
        String name = ApexPages.currentPage().getParameters().get('name');
        PageReference p = Page.TargetPage;
        p.getParameters().put('name', EncodingUtil.urlEncode(name, 'UTF-8'));
        p.getParameters().put('source', EncodingUtil.urlEncode('search', 'UTF-8'));
        p.setRedirect(true);
        return p;
    }
}
