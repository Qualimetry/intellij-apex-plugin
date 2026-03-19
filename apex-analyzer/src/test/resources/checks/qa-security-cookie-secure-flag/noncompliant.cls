public class InsecureCookieClass {
    public void setSessionCookie(String token) {
        Cookie c = new Cookie('session', token, null, 3600, false);
        ApexPages.currentPage().setCookies(new List<Cookie>{ c });
    }

    public void setPreferenceCookie(String pref) {
        Cookie c = new Cookie('pref', pref, '/', 86400, false);
        ApexPages.currentPage().setCookies(new List<Cookie>{ c });
    }
}
