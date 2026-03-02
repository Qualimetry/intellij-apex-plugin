public class HomeController {
    public PageReference goHome() {
        PageReference ref = Page.HomePage;
        ref.getParameters().put('tab', 'overview');
        ref.setRedirect(true);
        return ref;
    }

    public PageReference goProfile() {
        PageReference ref = Page.Profile;
        ref.setRedirect(true);
        return ref;
    }
}
