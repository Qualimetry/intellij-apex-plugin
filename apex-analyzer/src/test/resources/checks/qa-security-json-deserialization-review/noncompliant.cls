public class UsesJsonDeserialize {
    @AuraEnabled
    public static void handlePayload(String body) {
        Object o = JSON.deserializeUntyped(body);
        Map<String, Object> data = (Map<String, Object>) o;
        String action = (String) data.get('action');
    }

    public void processWebhook(String raw) {
        Map<String, Object> payload = (Map<String, Object>) JSON.deserializeUntyped(raw);
        String type = (String) payload.get('type');
    }
}
