# Hardcoded values should have explanatory comments

`qa-convention-comment-hardcoded-values` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; enabled in the recommended profile

Hardcoded numeric values (magic numbers) scattered through the code make it difficult to understand their purpose or to change them later. When a number like 200 appears without explanation, a maintainer cannot tell whether it represents a batch size, a limit, or an HTTP status code. Add an explanatory comment or extract the value into a named constant.

## Noncompliant code example

```apex
public class BatchProcessor {
    public void processBatch(List<SObject> records) {
        Integer batchSize = 200; // Noncompliant - no preceding comment explains 200
        Integer retryLimit = 50;

        for (Integer i = 0; i < records.size(); i += batchSize) {
            List<SObject> batch = new List<SObject>();
            executeBatch(batch);
        }
    }

    private void executeBatch(List<SObject> batch) { }
}
```

## Compliant solution

```apex
public class BatchProcessor {
    // Salesforce DML governor limit per transaction
    private static final Integer BATCH_SIZE = 200;
    // Maximum retry attempts before failing
    private static final Integer RETRY_LIMIT = 50;

    public void processBatch(List<SObject> records) {
        for (Integer i = 0; i < records.size(); i += BATCH_SIZE) {
            List<SObject> batch = new List<SObject>();
            executeBatch(batch);
        }
    }

    private void executeBatch(List<SObject> batch) { }
}
```
