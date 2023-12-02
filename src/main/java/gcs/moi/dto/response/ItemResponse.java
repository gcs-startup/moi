package gcs.moi.dto.response;

import gcs.moi.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {

    private Long id;
    private String title;
    private Long roomId;
    private Long ownerId;

    public static ItemResponse from(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getTitle(),
                item.getRoom().getId(),
                item.getOwner().getId());
    }
}
