public class SafeRedirect {
    private static final Set<String> ALLOWED_PATHS = new Set<String>{
        '/home', '/dashboard', '/settings'
    };

    public PageReference redirect() {
        String target = ApexPages.currentPage().getParameters().get('next');
        if (!ALLOWED_PATHS.contains(target)) {
            target = '/home';
        }
        return new PageReference(target);
    }

    @AuraEnabled
    public static String getRedirectUrl(String url) {
        if (url == null || !url.startsWith('/')) {
            return '/home';
        }
        return url;
    }
}
