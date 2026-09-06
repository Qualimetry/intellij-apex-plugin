public class NoRefererUse {
    public void checkSource() {
        String csrfToken = ApexPages.currentPage().getParameters().get('token');
        if (csrfToken == null || !CsrfUtil.isValid(csrfToken)) {
            throw new SecurityException('Invalid CSRF token');
        }
    }

    public Boolean isInternalRequest() {
        String userId = UserInfo.getUserId();
        return PermissionUtil.hasInternalAccess(userId);
    }
}
