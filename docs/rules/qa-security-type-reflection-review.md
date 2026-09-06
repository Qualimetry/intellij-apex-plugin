# Dynamic type reflection requires security review

`qa-security-type-reflection-review` &middot; Security &middot; Security Hotspot &middot; severity MAJOR &middot; enabled in the recommended profile

Dynamic type reflection via Type.forName() and newInstance() can instantiate arbitrary classes if the type name comes from an untrusted source. An attacker could cause execution of unintended code paths, access internal classes, or trigger initialization side effects. All uses of reflection must be reviewed and type names must be validated.

## Noncompliant code example

```apex
public class DynamicFactory {
    public static Object createHandler(String handlerName) {
        Type handlerType = Type.forName(handlerName);    // Noncompliant — review required
        return handlerType.newInstance();
    }

    public static void dispatch(String className, String methodName) {
        Type t = Type.forName(className);                // Noncompliant
        Object instance = t.newInstance();
    }
}
```

## Compliant solution

```apex
public class DynamicFactory {
    private static final Map<String, Type> ALLOWED = new Map<String, Type>{
        'AccountHandler' => AccountHandler.class,
        'ContactHandler' => ContactHandler.class
    };

    public static Object createHandler(String handlerName) {
        Type handlerType = ALLOWED.get(handlerName);
        if (handlerType == null) {
            throw new IllegalArgumentException('Unknown handler: ' + handlerName);
        }
        return handlerType.newInstance();
    }
}
```
