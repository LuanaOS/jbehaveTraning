package apis.user;

import apis.ApiException;
import apis.user.model.UserRequest;
import apis.user.model.UserResponse;
import client.BaseClient;
import client.model.Envelope;
import client.model.Request;

import javax.ws.rs.client.Entity;
import java.util.*;

public class UserApi {
    private final BaseClient<Object> client;

    public UserApi() {
        client = new BaseClient<>("http://www.mocky.io");
    }

    public UserResponse createUser(String sessionToken, Envelope<UserRequest> payload, String path) throws ApiException {
        Request<Envelope<UserRequest>> request = new Request<>();
        request.setPath(path);
        request.setPayload(Entity.json(payload));
        request.setReturnObjectType(LinkedHashMap.class);
        if (sessionToken != null) {
            Map<String, String> headers = getSessionTokenHeader(sessionToken);
            request.setHeaders(headers);
        }

        LinkedHashMap<String, Object> response = (LinkedHashMap<String, Object>) client.doPost(request);


        UserResponse userResponse = new UserResponse(client, response);
        return userResponse;
    }

    private Map<String, String> getSessionTokenHeader(String sessionToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put("sessionToken", sessionToken);
        return headers;
    }



}
