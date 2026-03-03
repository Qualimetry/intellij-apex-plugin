public class EncryptionHelper {
    private EncryptionHelper() { }

    public String encrypt(String value) {
        return EncodingUtil.base64Encode(Blob.valueOf(value));
    }

    public String decrypt(String value) {
        return EncodingUtil.base64Decode(value).toString();
    }
}
