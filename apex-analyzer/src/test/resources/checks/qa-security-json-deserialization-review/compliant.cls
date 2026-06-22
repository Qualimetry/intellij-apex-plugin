public class SafeJsonHandler {
    public class WebhookPayload {
        public String action;
        public String type;
    }

    @AuraEnabled
    public static String handlePayload(WebhookPayload wp) {
        return wp.action;
    }

    public String getType(WebhookPayload payload) {
        return payload.type;
    }
}
