public class FeatureToggleServiceCompliant {
    private String featureName;

    public FeatureToggleServiceCompliant(String featureName) {
        this.featureName = featureName;
    }

    public Boolean isFeatureEnabled() {
        Feature_Flag__mdt flag = Feature_Flag__mdt.getInstance(featureName);
        return flag != null && flag.Enabled__c;
    }
}
