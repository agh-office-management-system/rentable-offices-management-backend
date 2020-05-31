package pl.edu.agh.rentableoffices.tenant.model;

public class TenantBuilder {
    private String companyName;
    private Integer numberOfEmployees;
    private IdType idType;
    private String idDocumentNumber;
    private PreferredMeansOfCommunication preferredMeansOfCommunication;
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    public static TenantBuilder newPrivate(String firstName, String lastName) {
        TenantBuilder builder = new TenantBuilder();
        builder.firstName = firstName;
        builder.lastName = lastName;
        return builder;
    }

    public static TenantBuilder newLegal(String companyName, Integer numberOfEmployees) {
        TenantBuilder builder = new TenantBuilder();
        builder.companyName = companyName;
        builder.numberOfEmployees = numberOfEmployees;
        return builder;
    }

    public TenantBuilder identification(IdType type, String documentNumber) {
        this.idType = type;
        this.idDocumentNumber = documentNumber;
        return this;
    }

    public TenantBuilder phone(String phone) {
        this.phoneNumber = phone;
        return this;
    }

    public TenantBuilder email(String email) {
        this.email = email;
        return this;
    }

    public TenantBuilder password(String password) {
        this.password = password;
        return this;
    }

    public TenantBuilder preferredMeansOfCommunication(PreferredMeansOfCommunication preferredMeansOfCommunication) {
        this.preferredMeansOfCommunication = preferredMeansOfCommunication;
        return this;
    }

    public Tenant build() {
        return new Tenant(firstName, lastName, companyName, numberOfEmployees, companyName != null,
                idDocumentNumber, idType, preferredMeansOfCommunication, phoneNumber, email, password, TenantStatus.CREATED, null, null);
    }
}
