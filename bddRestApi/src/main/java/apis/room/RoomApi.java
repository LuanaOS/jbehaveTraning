package apis.room;

import apis.ApiException;
import apis.room.model.RoomRequest;
import apis.room.model.RoomResponse;
import client.BaseClient;
import client.model.Envelope;
import client.model.Request;

import javax.ws.rs.client.Entity;
import java.util.LinkedHashMap;
import java.util.Map;

public class RoomApi {
    private final BaseClient<Object> client;

    public RoomApi() {
        client = new BaseClient<>("http://www.mocky.io");
    }

    public RoomResponse createRoom(Envelope<RoomRequest> payload, String path) throws ApiException {
        Request<Envelope<RoomRequest>> request = new Request<>();
        request.setPath(path);
        request.setPayload(Entity.json(payload));
        request.setReturnObjectType(LinkedHashMap.class);

        LinkedHashMap<String, Object> response = (LinkedHashMap<String, Object>) client.doPost(request);

        RoomResponse roomResponse = new RoomResponse(client, response);
        return roomResponse;
    }
}
