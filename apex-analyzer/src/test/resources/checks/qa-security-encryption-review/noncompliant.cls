public class UsesEncryption {
    public Blob encrypt(Blob key, Blob iv, Blob data) {
        return Crypto.encrypt('AES128', key, iv, data);
    }

    public Blob decrypt(Blob key, Blob iv, Blob cipher) {
        return Crypto.decrypt('AES128', key, iv, cipher);
    }

    public Blob encryptWithMode(String algo, Blob key, Blob iv, Blob data) {
        return Crypto.encrypt(algo, key, iv, data);
    }
}
