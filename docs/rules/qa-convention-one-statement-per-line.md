# Each statement must appear on its own line

`qa-convention-one-statement-per-line` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; optional

This rule requires each statement to appear on its own line. Multiple statements on a single line reduce readability and make debugging harder since breakpoints target entire lines. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class CompactCode {
    public void process() {
        Integer x = 1; Integer y = 2; Integer z = x + y; // Noncompliant
    }
}
```

## Compliant solution

```apex
public class CompactCode {
    public void process() {
        Integer x = 1;
        Integer y = 2;
        Integer z = x + y;
    }
}
```
