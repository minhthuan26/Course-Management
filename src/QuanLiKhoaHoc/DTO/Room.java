package QuanLiKhoaHoc.DTO;

public class Room {
    int RoomId;
    String RoomName;

    public Room(int roomId, String roomName) {
        RoomId = roomId;
        RoomName = roomName;
    }

    public int getRoomId() {
        return RoomId;
    }

    public void setRoomId(int roomId) {
        RoomId = roomId;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    @Override
    public String toString() {
        return "Room{" +
                "RoomId=" + RoomId +
                ", RoomName='" + RoomName + '\'' +
                '}';
    }
}
