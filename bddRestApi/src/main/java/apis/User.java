package apis;

import com.sun.xml.internal.fastinfoset.util.StringArray;

public class User {
    private UserAttributes userattributes;
    private StringArray roles;

    public User(UserAttributes attributes, StringArray roles){
        setUserattributes(attributes);
        setRoles(roles);
    }

    public UserAttributes getUserattributes() {
        return userattributes;
    }

    public void setUserattributes(UserAttributes userattributes) {
        this.userattributes = userattributes;
    }

    public StringArray getRoles() {
        return roles;
    }

    public void setRoles(StringArray roles) {
        this.roles = roles;
    }
}
