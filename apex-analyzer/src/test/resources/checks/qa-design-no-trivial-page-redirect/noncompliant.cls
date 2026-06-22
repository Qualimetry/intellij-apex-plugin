public class HomeController {
    public PageReference goHome() {
        return Page.HomePage; setRedirect(true);
    }

    public PageReference goDash() {
        return Page.Dashboard; setRedirect(true);
    }

    public PageReference goProfile() {
        PageReference ref = Page.Profile;
        ref.setRedirect(true);
        return ref;
    }
}
