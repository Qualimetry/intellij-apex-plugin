# Cryptographic operations must use random IV/key

`qa-security-random-iv-key` &middot; Security &middot; Security Hotspot &middot; severity CRITICAL &middot; enabled in the recommended profile

Cryptographic operations that use a fixed or predictable initialization vector (IV) or encryption key severely weaken the confidentiality of encrypted data. An attacker who knows or can guess the IV/key can decrypt all messages encrypted with that value, turning encryption into a false sense of security. Always generate random IVs and keys using Crypto.generateAesKey().

## Noncompliant code example

```apex
public class PaymentEncryptor {
    private static final String FIXED_KEY = 'MySuperSecretKey';
    private static final String FIXED_IV  = '0000000000000000';

    public static Blob encryptCard(String cardNumber) {
        Blob key  = Blob.valueOf(FIXED_KEY);            // Noncompliant
        Blob iv   = Blob.valueOf(FIXED_IV);             // Noncompliant
        Blob data = Blob.valueOf(cardNumber);
        return Crypto.encrypt('AES128', key, iv, data);
    }
}
```

## Compliant solution

```apex
public class PaymentEncryptor {
    public static Blob encryptCard(String cardNumber) {
        Blob key  = Crypto.generateAesKey(256);
        Blob iv   = Crypto.generateAesKey(128);
        Blob data = Blob.valueOf(cardNumber);
        return Crypto.encrypt('AES256', key, iv, data);
    }
}
```
