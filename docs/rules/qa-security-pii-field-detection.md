# PII fields must be flagged

`qa-security-pii-field-detection` &middot; Security &middot; Security Hotspot &middot; severity CRITICAL &middot; enabled in the recommended profile

Fields whose names indicate personally identifiable information (PII) — such as social security numbers, credit card numbers, passport numbers, or dates of birth — must be reviewed for proper access control, encryption at rest, and data masking. Unprotected PII fields create regulatory compliance risks (GDPR, CCPA, HIPAA) and can lead to severe data breach consequences.

## Noncompliant code example

```apex
public class CustomerRecord {
    public String SocialSecurityNumber;       // Noncompliant — PII field
    public String CreditCard__c;              // Noncompliant — PII custom field
    public String Email__c;                   // Noncompliant — PII custom field
    public Date BirthDate__c;                 // Noncompliant — PII custom field
    public String DriversLicense;             // Noncompliant — PII field
    public Integer orderCount;
}
```

## Compliant solution

```apex
public class CustomerRecord {
    // Reviewed: encrypted via Shield Platform Encryption
    // FLS enforced, access audited
    private String SocialSecurityNumber;
    private String CreditCard__c;
    private String Email__c;
    private Date BirthDate__c;
    private String DriversLicense;
    public Integer orderCount;
}
```
