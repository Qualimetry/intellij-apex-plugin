# =+ and =- must not be used in place of += and -=

`qa-error-handling-no-reversed-operator` &middot; Error Handling &middot; Bug &middot; severity CRITICAL &middot; optional

This rule flags the use of =+ and =- which are not compound assignment operators. They are parsed as assignment followed by a unary operator, producing unexpected results. The intended operators are += and -=. This rule overlaps with SonarQube's built-in Apex analysis. It is excluded from the default profile to avoid duplicate findings.

## Noncompliant code example

```apex
public class ScoreTracker {
    private Integer score = 0;

    public void addPoints(Integer points) {
        score =+ points; // Noncompliant — assigns +points, does not add
    }

    public void deductPoints(Integer points) {
        score =- points; // Noncompliant — assigns -points, does not subtract
    }
}
```

## Compliant solution

```apex
public class ScoreTracker {
    private Integer score = 0;

    public void addPoints(Integer points) {
        score += points;
    }

    public void deductPoints(Integer points) {
        score -= points;
    }
}
```
