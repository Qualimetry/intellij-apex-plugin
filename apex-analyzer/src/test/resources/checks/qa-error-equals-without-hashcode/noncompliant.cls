public class ContactKey {
    private String email;

    public ContactKey(String email) {
        this.email = email;
    }

    public Boolean equals(Object other) {
        if (other instanceof ContactKey) {
            return this.email == ((ContactKey) other).email;
        }
        return false;
    }
}
