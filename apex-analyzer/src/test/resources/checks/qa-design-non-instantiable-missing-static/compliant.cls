public class EncryptionHelper {
    private EncryptionHelper() { }

    public static String encrypt(String value) {
        return EncodingUtil.base64Encode(Blob.valueOf(value));
    }

    public static String decrypt(String value) {
        return EncodingUtil.base64Decode(value).toString();
    }
}
