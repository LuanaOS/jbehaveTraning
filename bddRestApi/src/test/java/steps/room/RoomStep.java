package steps.room;

import apis.room.RoomApi;
import apis.room.model.RoomAttributes;
import apis.room.model.RoomRequest;
import apis.room.model.RoomResponse;
import client.model.Envelope;
import org.jbehave.core.annotations.*;

public class RoomStep {

    @Given("an user $userId has the role $role")
    @Pending
    public void checkUserRole(String userId, String role) {
        //PENDING
    }

    @Then("create a room")
    public void createARoom() {
        RoomRequest roomRequest = buildRoomAttributes();

        RoomApi roomApi = new RoomApi();
        // ENDPOINT field visibility
//        RoomResponse roomResponse = roomApi.createRoom(new Envelope<>(roomRequest), "/v2/5bbfc2f03200004e006a3006");
        
        // ENDPOINT field public
                RoomResponse roomResponse = roomApi.createRoom(new Envelope<>(roomRequest), "/v2/5bab95f83100004c00654436");

    }

    private RoomRequest buildRoomAttributes(String name, String description, boolean membersCanInvite, boolean discoverable, boolean visibility) {
        RoomRequest roomRequest = new RoomRequest();
        RoomAttributes roomAttributes = new RoomAttributes(name, description, membersCanInvite, discoverable, visibility);
        roomRequest.setRoomAttributes(roomAttributes);
        return roomRequest;
    }

    private RoomRequest buildRoomAttributes() {
        RoomRequest roomRequest = new RoomRequest();
        RoomAttributes roomAttributes = new RoomAttributes("API room", "Created via the API", true, true, false);
        roomRequest.setRoomAttributes(roomAttributes);
        return roomRequest;
    }


}
