# Source files must use spaces instead of tabs

`qa-convention-no-tab-characters` &middot; Convention &middot; Code Smell &middot; severity MINOR &middot; optional

This rule flags source files that contain tab characters for indentation. Tabs render at different widths in different editors, causing inconsistent alignment. Use spaces for consistent formatting across all tools and environments. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class TabExample {
&#9;public void doWork() { // Noncompliant — uses tab
&#9;&#9;System.debug(LoggingLevel.INFO, 'working');
&#9;}
}
```

## Compliant solution

```apex
public class TabExample {
    public void doWork() {
        System.debug(LoggingLevel.INFO, 'working');
    }
}
```
