package apis.user.model;

import client.BaseClient;

import java.util.LinkedHashMap;
import java.util.List;

public class UserResponse {

    private UserAttributes userAttributes;
    private UserSystemInfo userSystemInfo;
    private List<String> roles;
    private String sessionToken;

    public UserResponse(){
    }

    public UserResponse(BaseClient<Object> client, LinkedHashMap<String, Object> resp){
        setUserAttributes((UserAttributes) client.convertFromLinkedHashMap(UserAttributes.class, (LinkedHashMap) resp.get("userAttributes")));
        setUserSystemInfo((UserSystemInfo) client.convertFromLinkedHashMap(UserSystemInfo.class, (LinkedHashMap) resp.get("userSystemInfo")));
        setRoles((List<String>) resp.get("roles"));
        setSessionToken((String) resp.get("sessionToken"));
    }

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
        return this.sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
