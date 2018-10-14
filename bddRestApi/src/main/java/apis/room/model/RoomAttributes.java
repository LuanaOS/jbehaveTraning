package apis.room.model;

public class RoomAttributes {
    private String name;
    private String description;
    private boolean membersCanInvite;
    private boolean discoverable;
    private boolean visibility;

    public  RoomAttributes(){}

    public RoomAttributes(String name, String description, boolean membersCanInvite, boolean discoverable, boolean visibility) {
        this.name = name;
        this.description = description;
        this.membersCanInvite = membersCanInvite;
        this.discoverable = discoverable;
        this.visibility = visibility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isMembersCanInvite() {
        return membersCanInvite;
    }

    public void setMembersCanInvite(boolean membersCanInvite) {
        this.membersCanInvite = membersCanInvite;
    }

    public boolean isDiscoverable() {
        return discoverable;
    }

    public void setDiscoverable(boolean discoverable) {
        this.discoverable = discoverable;
    }

    public boolean isPublic() {
        return visibility;
    }

    public void setPublic(boolean visibility) {
        this.visibility = visibility;
    }
}
