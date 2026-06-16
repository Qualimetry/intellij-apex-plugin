public class WeakDigest {
    public Blob hashData(Blob data) {
        return Crypto.hash('MD5', data);
    }

    public Blob verifyIntegrity(String payload) {
        Blob input = Blob.valueOf(payload);
        return Crypto.hash('SHA1', input);
    }

    public Blob generateFingerprint(Blob content) {
        return Crypto.hash('MD5', content);
    }
}
