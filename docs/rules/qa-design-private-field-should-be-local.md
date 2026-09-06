# Private fields used only in one method should be local variables

`qa-design-private-field-should-be-local` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A private field that is read and written only within a single method does not need class-level scope. Declaring it as a local variable instead reduces the class surface area, avoids unintended state sharing between method calls, and makes the method self-contained.

## Noncompliant code example

```apex
public class ReportGenerator {
    private String tempBuffer; // Noncompliant - only used in generateReport
    private Integer lineCount; // Noncompliant - only used in generateReport

    public String generateReport(List<Account> accounts) {
        tempBuffer = '';
        lineCount = 0;
        for (Account acc : accounts) {
            tempBuffer += acc.Name + '\n';
            lineCount++;
        }
        return tempBuffer;
    }
}
```

## Compliant solution

```apex
public class ReportGenerator {
    public String generateReport(List<Account> accounts) {
        String buffer = '';
        Integer lineCount = 0;
        for (Account acc : accounts) {
            buffer += acc.Name + '\n';
            lineCount++;
        }
        return buffer;
    }
}
```
