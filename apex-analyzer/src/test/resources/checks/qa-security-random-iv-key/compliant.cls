public class SecureCrypto {
    public Blob encryptData(Blob plaintext) {
        Blob randomKey = Crypto.generateAesKey(256);
        Blob randomIv = Crypto.generateAesKey(128);
        return Crypto.encrypt('AES256', randomKey, randomIv, plaintext);
    }

    public Blob decryptData(Blob ciphertext) {
        Blob randomKey = Crypto.generateAesKey(256);
        Blob randomIv = Crypto.generateAesKey(128);
        return Crypto.decrypt('AES256', randomKey, randomIv, ciphertext);
    }
}
