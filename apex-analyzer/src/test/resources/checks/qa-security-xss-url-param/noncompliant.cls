public class RedirectController {
    public String userInput { get; set; }

    public PageReference redirect() {
        String name = ApexPages.currentPage().getParameters().get('name');
        PageReference p = Page.TargetPage;
        p.getParameters().put('name', name);
        p.getParameters().put('source', 'search');
        p.setRedirect(true);
        return p;
    }
}
