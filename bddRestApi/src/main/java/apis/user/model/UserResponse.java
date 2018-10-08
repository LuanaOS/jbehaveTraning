package apis.user.model;

import java.util.List;

public class UserResponse {

    private UserAttributes userAttributes;
    private UserSystemInfo userSystemInfo;
    private List<String> roles;
    private String sessionToken;

    public UserAttributes getUserAttributes() {
        return userAttributes;
    }

    public void setUserAttributes(UserAttributes userAttributes) {
        this.userAttributes = userAttributes;
    }

    public UserSystemInfo getUserSystemInfo() {
        return userSystemInfo;
    }

    public void setUserSystemInfo(UserSystemInfo userSystemInfo) {
        this.userSystemInfo = userSystemInfo;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
