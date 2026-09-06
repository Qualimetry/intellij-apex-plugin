# Source lines must not exceed max character length

`qa-convention-line-length-limit` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; optional

This rule flags source lines that exceed the configured maximum character length. Overly long lines require horizontal scrolling and make side-by-side diffs difficult to read. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class LongLineExample {
    public void doWork() {
        System.debug(LoggingLevel.INFO, 'This is a very long debug message that exceeds the maximum allowed line length and should be split'); // Noncompliant
    }
}
```

## Compliant solution

```apex
public class LongLineExample {
    public void doWork() {
        System.debug(LoggingLevel.INFO,
            'This debug message has been split across lines');
    }
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| maxLength | Maximum number of characters allowed in a source line | `120` |
