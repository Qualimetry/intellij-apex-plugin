public class SecureCookieClass {
    public void setSessionCookie(String token) {
        Cookie c = new Cookie('session', token, null, 3600, true);
        ApexPages.currentPage().setCookies(new List<Cookie>{ c });
    }

    public void setPreferenceCookie(String pref) {
        Cookie c = new Cookie('pref', pref, '/', 86400, true);
        ApexPages.currentPage().setCookies(new List<Cookie>{ c });
    }
}
