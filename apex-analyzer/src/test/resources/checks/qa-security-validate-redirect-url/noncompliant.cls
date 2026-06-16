public class UnsafeRedirect {
    public PageReference redirect() {
        return new PageReference(ApexPages.currentPage().getParameters().get('next'));
    }

    public PageReference safeRedirect(String url) {
        PageReference ref = new PageReference(url);
        ref.setRedirectUrl(url);
        return ref;
    }
}
