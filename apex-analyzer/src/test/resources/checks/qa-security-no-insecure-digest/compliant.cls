public class StrongDigest {
    public Blob hashData(Blob data) {
        return Crypto.hash('SHA-256', data);
    }

    public Blob verifyIntegrity(String payload) {
        Blob input = Blob.valueOf(payload);
        return Crypto.hash('SHA-256', input);
    }

    public Blob generateFingerprint(Blob content) {
        return Crypto.hash('SHA-512', content);
    }
}
