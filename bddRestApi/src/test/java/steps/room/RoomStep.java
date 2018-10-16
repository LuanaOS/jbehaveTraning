package steps.room;

import apis.ApiException;
import apis.room.RoomApi;
import apis.room.model.RoomAttributes;
import apis.room.model.RoomRequest;
import apis.room.model.RoomResponse;
import apis.user.model.UserAttributes;
import apis.user.model.UserRequest;
import apis.user.model.UserResponse;
import client.model.Envelope;
import org.apache.http.HttpStatus;
import org.jbehave.core.annotations.*;
import org.junit.Assert;
import steps.user.UserStep;

import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class RoomStep {

    @Given("verify if user $userId has role as CREATE_ROOM")
    public void checkUserRole(String userId) {

        UserResponse userResponse = UserStep.appContxt.lookupApiUserReference(userId);

        String roles = "CREATE_ROOM";

        if (userResponse.getRoles().contains(roles)){
            RoomRequest roomRequest = buildRoomAttributes();
            String path = "/v2/5bab97d1310000100065444b";

            RoomApi roomApi = new RoomApi();

            try {
                RoomResponse roomResponse = roomApi.createRoom(new Envelope<>(roomRequest), path);
                fail("Expect code error: " + Response.Status.FORBIDDEN);
            } catch (ApiException e){
                Assert.assertEquals(HttpStatus.SC_FORBIDDEN, e.getErrorCode());
            }
        }
    }

    @Then("create a room")
    public void createRoom(){
        RoomRequest roomRequest = buildRoomAttributes();

        RoomApi roomApi = new RoomApi();

        RoomResponse roomResponse = roomApi.createRoom(new Envelope<>(roomRequest), "/v2/5bab95f83100004c00654436");

        if (roomResponse.getRoomSystemInfo().isActive() == true)
            assertRoom(roomRequest, roomResponse);
        else
            fail("Expect code error: " + Response.Status.FORBIDDEN);
    }

    @Then("a room cannot be created without field $field")
    public void createRoomWithoutField(String field) throws ApiException {

        RoomRequest roomRequest = buildRoomAttributes();
        String path = new String();

        if (field.equals("name")) {
            roomRequest.getRoomAttributes().setName(null);
            path = "/v2/5bab974b3100007000654446";
        }

        if (field.equals("description")){
            roomRequest.getRoomAttributes().setDescription(null);
            path = "/v2/5bab97223100006400654445";
        }

        RoomApi roomApi = new RoomApi();

        try {
            RoomResponse roomResponse = roomApi.createRoom(new Envelope<>(roomRequest), path);
            fail("Expect code error: " + Response.Status.BAD_REQUEST);
        } catch (ApiException e){
                Assert.assertEquals(HttpStatus.SC_BAD_REQUEST, e.getErrorCode());
        }
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

    private void assertRoom(RoomRequest roomRequest, RoomResponse roomResponse) {
        Assert.assertEquals("name should be valid", roomResponse.getRoomAttributes().getName(), roomRequest.getRoomAttributes().getName());
        Assert.assertEquals("description should be valid", roomResponse.getRoomAttributes().getDescription(), roomRequest.getRoomAttributes().getDescription());
        Assert.assertEquals("membersCanInvite should be valid", roomResponse.getRoomAttributes().isMembersCanInvite(), roomRequest.getRoomAttributes().isMembersCanInvite());
        Assert.assertEquals("discoverable should be valid", roomResponse.getRoomAttributes().isDiscoverable(), roomRequest.getRoomAttributes().isDiscoverable());
        Assert.assertEquals("public should be valid", roomResponse.getRoomAttributes().isPublic(), roomRequest.getRoomAttributes().isPublic());
    }


    private UserRequest buildUserAttributes() {
        UserRequest userRequest = new UserRequest();
        UserAttributes userAttributes = new UserAttributes("janedoe@symphony.com", "Jane", "Doe", "janedoe");
        userRequest.setUserAttributes(userAttributes);

        List<String> roles = new ArrayList<>();
        roles.add("CREATE_ROOM");
        userRequest.setRoles(roles);
        return userRequest;
    }

}
