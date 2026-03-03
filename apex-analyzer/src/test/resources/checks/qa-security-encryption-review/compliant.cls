public class NoEncryption {
    public Blob hashData(Blob data) {
        return Crypto.hash('SHA-256', data);
    }

    public Blob signData(Blob privateKey, Blob data) {
        return Crypto.sign('RSA-SHA256', data, privateKey);
    }
}
