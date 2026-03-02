public class InsecureCrypto {
    private static final String SECRET = 'MySuperSecretKey';
    private static final String IV_VALUE = '0000000000000000';

    public Blob encryptData(Blob plaintext) {
        Blob key = Blob.valueOf(SECRET);
        Blob iv = Blob.valueOf(IV_VALUE);
        return Crypto.encrypt('AES128', key, iv, plaintext);
    }

    public Blob encryptCard(String cardNumber) {
        Blob key = Blob.valueOf('fixed-secret-key');
        Blob iv = Blob.valueOf('fixed-iv-16-bytes!!');
        return Crypto.encrypt('AES128', key, iv, Blob.valueOf(cardNumber));
    }
}
