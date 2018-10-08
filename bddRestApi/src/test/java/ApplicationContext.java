import apis.user.model.UserResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    private Map<String, UserResponse> adminApiAppsReferences = new ConcurrentHashMap<>();

    public void addAdminApiUserReference(String userId, UserResponse userResponse) {


        if (adminApiAppsReferences.containsKey(userId)) {
            try {
                throw new Exception(userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        adminApiAppsReferences.put(userId, userResponse);
    }

    public UserResponse lookupAdminApiAppReference(String userId) {
        UserResponse userResponse = adminApiAppsReferences.get(userId);
        if (userResponse == null) {
            try {
                throw new Exception(userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userResponse;
    }

}
