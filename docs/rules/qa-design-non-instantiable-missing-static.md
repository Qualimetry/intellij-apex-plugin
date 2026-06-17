# Non-instantiable classes must provide static methods

`qa-design-non-instantiable-missing-static` &middot; Design &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

A class with only a private constructor cannot be instantiated from outside. If it also has no static methods, it serves no purpose since no code can ever access its functionality. Either add static utility methods to justify the private constructor, or make the constructor accessible.

## Noncompliant code example

```apex
public class EncryptionHelper {
    private EncryptionHelper() { } // Noncompliant - private ctor but no static methods

    public String encrypt(String value) {
        return EncodingUtil.base64Encode(Blob.valueOf(value));
    }

    public String decrypt(String value) {
        return EncodingUtil.base64Decode(value).toString();
    }
}
```

## Compliant solution

```apex
public class EncryptionHelper {
    private EncryptionHelper() { }

    public static String encrypt(String value) {
        return EncodingUtil.base64Encode(Blob.valueOf(value));
    }

    public static String decrypt(String value) {
        return EncodingUtil.base64Decode(value).toString();
    }
}
```
