public class ContactWrapper {
    public String firstName { get; set; }
    public String lastName { get; set; }
    public Boolean isActive { get; set; }

    public ContactWrapper(String first, String last) {
        this.firstName = first;
        this.lastName = last;
        this.isActive = true;
    }

    public String fullName() {
        return firstName + ' ' + lastName;
    }
}
