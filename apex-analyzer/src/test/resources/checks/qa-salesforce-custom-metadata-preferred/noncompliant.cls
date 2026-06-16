public class FeatureToggleService {
    private String featureName;

    public FeatureToggleService(String featureName) {
        this.featureName = featureName;
    }

    public Boolean isFeatureEnabled() {
        App_Config__c config = App_Config__c.getOrgDefaults();
        if (featureName == 'BetaUI') {
            return config.Enable_Beta_UI__c;
        }
        return false;
    }
}
