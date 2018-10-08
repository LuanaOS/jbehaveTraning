package apis.user.model;

public class UserAttributes {
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String userName;

    public UserAttributes(){
    }

    public UserAttributes(String email, String firstName, String lastName, String userName){
        this.emailAddress = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }


    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
