package apis;

public class UserAttributes {
    private String email;
    private String fname;
    private String lname;
    private String username;

    public UserAttributes(String email, String fname, String lname, String username){
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
