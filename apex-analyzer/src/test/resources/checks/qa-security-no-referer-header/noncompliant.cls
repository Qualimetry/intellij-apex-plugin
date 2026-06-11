public class UsesReferer {
    public void checkSource() {
        String ref = ApexPages.currentPage().getHeaders().get('Referer');
        if (ref == null || !ref.contains('salesforce.com')) {
            throw new SecurityException('Invalid source');
        }
    }

    public Boolean isInternalRequest() {
        String referer = ApexPages.currentPage().getHeaders().get('Referer');
        return referer != null && referer.startsWith('https://internal.');
    }
}
