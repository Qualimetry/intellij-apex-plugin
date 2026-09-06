# For-loop update expressions should modify the loop variable

`qa-error-handling-suspicious-loop-incrementer` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A for-loop update expression that does not modify the declared loop variable is suspicious and likely a bug. The loop may run infinitely or behave unpredictably because the termination condition depends on a variable that never changes.

## Noncompliant code example

```apex
public class ArrayWalker {
    public void walk(List<String> items) {
        Integer other = 0;
        for (Integer i = 0; i < items.size(); other++) { // Noncompliant - increments other, not i
            System.debug(items[i]);
        }
    }

    public void scan(List<Integer> nums) {
        Integer k = 0;
        for (Integer j = 0; j < nums.size(); k++) { // Noncompliant - increments k, not j
            System.debug(nums[j]);
        }
    }
}
```

## Compliant solution

```apex
public class ArrayWalker {
    public void walk(List<String> items) {
        for (Integer i = 0; i < items.size(); i++) {
            System.debug(items[i]);
        }
    }

    public void scan(List<Integer> nums) {
        for (Integer j = 0; j < nums.size(); j++) {
            System.debug(nums[j]);
        }
    }
}
```
