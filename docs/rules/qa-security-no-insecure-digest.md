# Use strong digest algorithms instead of deprecated ones

`qa-security-no-insecure-digest` &middot; Security &middot; Security Hotspot &middot; severity MAJOR &middot; enabled in the recommended profile

Hash algorithms such as MD5 and SHA-1 are cryptographically broken — collision attacks against them are practical and well-documented. Using these algorithms to hash passwords, tokens, or integrity-critical data allows attackers to forge hashes or recover plaintext. Use SHA-256 or stronger algorithms via Crypto.hash().

## Noncompliant code example

```apex
public class TokenService {
    public static String hashToken(String token) {
        Blob digest = Crypto.hash('MD5', Blob.valueOf(token));     // Noncompliant
        return EncodingUtil.convertToHex(digest);
    }

    public static String hashPassword(String password) {
        Blob digest = Crypto.hash('SHA1', Blob.valueOf(password)); // Noncompliant
        return EncodingUtil.convertToHex(digest);
    }
}
```

## Compliant solution

```apex
public class TokenService {
    public static String hashToken(String token) {
        Blob digest = Crypto.hash('SHA-256', Blob.valueOf(token));
        return EncodingUtil.convertToHex(digest);
    }

    public static String hashPassword(String password) {
        Blob digest = Crypto.hash('SHA-256', Blob.valueOf(password));
        return EncodingUtil.convertToHex(digest);
    }
}
```
