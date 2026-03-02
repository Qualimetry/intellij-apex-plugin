public class NoCookieUsage {
    public void storePreference(String name, String value) {
        UserPreference__c pref = new UserPreference__c();
        pref.Name = name;
        pref.Value__c = value;
        upsert pref Name;
    }

    public String readPreference(String name) {
        List<UserPreference__c> prefs = [SELECT Value__c FROM UserPreference__c
                                         WHERE Name = :name LIMIT 1];
        return prefs.isEmpty() ? null : prefs[0].Value__c;
    }
}
