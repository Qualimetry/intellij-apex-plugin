# Loop incrementers must correspond to the correct loop variable

`qa-error-handling-jumbled-incrementer` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A loop that increments a variable other than its own loop counter is almost always a copy-paste error. The wrong variable is updated, causing the loop to behave unexpectedly — potentially running infinitely or skipping iterations.

## Noncompliant code example

```apex
public class MatrixProcessor {
    public void process(List<List<Integer>> matrix) {
        for (Integer i = 0; i < matrix.size(); i++) {
            for (Integer j = 0; j < matrix[i].size(); i++) { // Noncompliant - increments i instead of j
                System.debug(matrix[i][j]);
            }
        }
    }
}
```

## Compliant solution

```apex
public class MatrixProcessor {
    public void process(List<List<Integer>> matrix) {
        for (Integer i = 0; i < matrix.size(); i++) {
            for (Integer j = 0; j < matrix[i].size(); j++) {
                System.debug(matrix[i][j]);
            }
        }
    }
}
```
