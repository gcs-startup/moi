package gcs.moi.dto.response;

import gcs.moi.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemSaveResponse {

    private Long id;
    private String title;
    private Long roomId;
    private Long ownerId;

    public static ItemSaveResponse from(Item item) {
        return new ItemSaveResponse(
                item.getId(),
                item.getTitle(),
                item.getRoom().getId(),
                item.getOwner().getId());
    }
}
