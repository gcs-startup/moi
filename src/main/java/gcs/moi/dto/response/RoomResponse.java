package gcs.moi.dto.response;

import gcs.moi.domain.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {

    private Long id;
    private String title;

    public static RoomResponse from(Room room) {
        return new RoomResponse(
                room.getId(),
                room.getTitle());
    }
}
