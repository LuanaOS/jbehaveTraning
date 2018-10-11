package apis.room.model;

import client.BaseClient;

import java.util.LinkedHashMap;

public class RoomResponse {
    private RoomAttributes roomAttributes;
    private RoomSystemInfo roomSystemInfo;

    public RoomResponse(){
    }

    public RoomResponse(BaseClient<Object> client, LinkedHashMap<String, Object> resp){
        setRoomAttributes((RoomAttributes) client.convertFromLinkedHashMap(RoomAttributes.class, (LinkedHashMap) resp.get("roomAttributes")));
        setRoomSystemInfo((RoomSystemInfo) client.convertFromLinkedHashMap(RoomSystemInfo.class, (LinkedHashMap) resp.get("roomSystemInfo")));
    }

    public RoomAttributes getRoomAttributes() {
        return roomAttributes;
    }

    public void setRoomAttributes(RoomAttributes roomAttributes) {
        this.roomAttributes = roomAttributes;
    }

    public RoomSystemInfo getRoomSystemInfo() {
        return roomSystemInfo;
    }

    public void setRoomSystemInfo(RoomSystemInfo roomSystemInfo) {
        this.roomSystemInfo = roomSystemInfo;
    }
}
