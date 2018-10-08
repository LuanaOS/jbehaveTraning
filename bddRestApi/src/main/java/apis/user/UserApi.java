package apis.user;

import client.BaseClient;
import client.model.Envelope;
import client.model.Request;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserApi {
    private final BaseClient<Object> client;

    public UserApi() {
        client = null;
    }

    public UserApi(String baseUrl) {
        //client = new BaseClient<>(baseUrl);
        //OU VC PODE CRAVAR A baseUrl
        client = new BaseClient<>("http://www.mocky.io");
    }

    public Response createUser(String sessionToken, Envelope<Request> payload) throws Exception {
        Request<Envelope<Request>> request = new Request<>();
        request.setPath("/user");
        request.setPayload(Entity.json(payload));
        request.setReturnObjectType(LinkedHashMap.class);
        Map<String, String> headers = getSessionTokenHeader(sessionToken);
        request.setHeaders(headers);
        LinkedHashMap<String, Object> response = (LinkedHashMap<String, Object>) client.doPost(request);
        return (Response) client.convertFromLinkedHashMap(Response.class, (LinkedHashMap) response.get("data"));
    }

    private Map<String, String> getSessionTokenHeader(String sessionToken) {
        Map<String, String> headers = new HashMap<>();
        headers.put("sessionToken", sessionToken);
        return headers;
    }

}
