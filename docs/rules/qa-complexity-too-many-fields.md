# Classes must not declare too many fields

`qa-complexity-too-many-fields` &middot; Complexity &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

Classes with too many fields are trying to represent too many concepts at once, violating the Single Responsibility Principle. High field counts increase the cognitive load needed to understand the class, expand the state space that must be tested, and make the class fragile to change. Group related fields into value objects or extract sub-components.

## Noncompliant code example

```apex
public class CustomerProfile {                     // Noncompliant — too many fields
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private Date birthDate;
    private String ssn;
    private String licenseNumber;
    // ... 15+ fields
}
```

## Compliant solution

```apex
public class CustomerProfile {
    private PersonName name;
    private ContactInfo contact;
    private Address address;
    private Identity identity;
}

public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
```

## Configuration

| Parameter | Description | Default |
| --- | --- | --- |
| threshold | Maximum number of fields per class | `20` |
