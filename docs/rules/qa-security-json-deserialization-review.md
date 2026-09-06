# JSON deserialization requires explicit type validation

`qa-security-json-deserialization-review` &middot; Security &middot; Security Hotspot &middot; severity MAJOR &middot; enabled in the recommended profile

Deserializing JSON from untrusted sources using JSON.deserializeUntyped() can lead to type confusion, unexpected object graphs, or injection of malicious data structures. An attacker who controls the input payload can inject arbitrary field values that bypass validation logic. Use JSON.deserializeStrict() with an explicit target type and validate the result before use.

## Noncompliant code example

```apex
public class WebhookHandler {
    @HttpPost
    global static void handleWebhook() {
        String body = RestContext.request.requestBody.toString();
        Object payload = JSON.deserializeUntyped(body);          // Noncompliant
        Map<String, Object> data = (Map<String, Object>) payload;
        String action = (String) data.get('action');
        processAction(action, data);
    }
}
```

## Compliant solution

```apex
public class WebhookHandler {
    public class WebhookPayload {
        public String action;
        public String recordId;
    }

    @HttpPost
    global static void handleWebhook() {
        String body = RestContext.request.requestBody.toString();
        WebhookPayload payload = (WebhookPayload)
            JSON.deserializeStrict(body, WebhookPayload.class);
        if (String.isBlank(payload.action)) return;
        processAction(payload.action, payload.recordId);
    }
}
```
