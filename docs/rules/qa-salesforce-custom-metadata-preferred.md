# Prefer Custom Metadata Types over Custom Settings

`qa-salesforce-custom-metadata-preferred` &middot; Salesforce &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

This rule flags usage of Custom Settings accessed via getOrgDefaults() and suggests migrating to Custom Metadata Types. Custom Metadata Types are deployable with change sets and packages, cached by the platform, and support relationships to other metadata. Custom Settings require manual data migration between environments and do not support the same deployment flexibility.

## Noncompliant code example

```apex
public class FeatureToggleService {
    public Boolean isFeatureEnabled(String featureName) {
        App_Config__c config = App_Config__c.getOrgDefaults(); // Noncompliant
        if (featureName == 'BetaUI') {
            return config.Enable_Beta_UI__c;
        }
        return false;
    }

    public Integer getMaxRetries() {
        Retry_Settings__c settings = Retry_Settings__c.getOrgDefaults(); // Noncompliant
        return (Integer) settings.Max_Retries__c;
    }
}
```

## Compliant solution

```apex
public class FeatureToggleService {
    public Boolean isFeatureEnabled(String featureName) {
        Feature_Flag__mdt flag = Feature_Flag__mdt.getInstance(featureName);
        return flag != null && flag.Enabled__c;
    }

    public Integer getMaxRetries() {
        App_Config__mdt config = App_Config__mdt.getInstance('Default');
        return config != null ? (Integer) config.Max_Retries__c : 3;
    }
}
```
