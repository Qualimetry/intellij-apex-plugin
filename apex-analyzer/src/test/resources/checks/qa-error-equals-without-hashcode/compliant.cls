public class ContactKeyCompliant {
    private String email;

    public ContactKeyCompliant(String email) {
        this.email = email;
    }

    public Boolean equals(Object other) {
        if (other instanceof ContactKeyCompliant) {
            return this.email == ((ContactKeyCompliant) other).email;
        }
        return false;
    }

    public Integer hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
