# Data encryption must be reviewed for correctness

`qa-security-encryption-review` &middot; Security &middot; Security Hotspot &middot; severity CRITICAL &middot; enabled in the recommended profile

Data encryption is security-sensitive and must be implemented correctly. Common mistakes include using weak algorithms (DES, RC4), hardcoded keys, ECB mode, or failing to authenticate ciphertext. Incorrect encryption gives a false sense of security while data remains vulnerable. All uses of Crypto.encrypt(), Crypto.encryptWithManagedIV(), and related methods require security review.

## Noncompliant code example

```apex
public class DataProtection {
    private static final String KEY = 'HardcodedKey12345';

    public static Blob protect(String sensitiveData) {
        Blob key  = Blob.valueOf(KEY);                     // Noncompliant — hardcoded key
        Blob data = Blob.valueOf(sensitiveData);
        return Crypto.encrypt('AES128', key, key, data);   // Noncompliant — IV equals key
    }
}
```

## Compliant solution

```apex
public class DataProtection {
    public static Blob protect(String sensitiveData) {
        Blob key  = Crypto.generateAesKey(256);
        Blob data = Blob.valueOf(sensitiveData);
        return Crypto.encryptWithManagedIV('AES256', key, data);
    }
}
```
