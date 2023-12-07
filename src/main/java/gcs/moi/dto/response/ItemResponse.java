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
    private String description;
    private Long amount;
    private Long roomId;
    private Long ownerId;
    private Long investAmount;

    private ItemResponse(Long id, String title, String description, Long amount, Long roomId, Long ownerId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.roomId = roomId;
        this.ownerId = ownerId;
    }

    public static ItemResponse from(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getTitle(),
                item.getDescription(),
                item.getAmount(),
                item.getRoom().getId(),
                item.getOwner().getId());
    }

    public static ItemResponse fromWithMemberAmount(ItemResponse itemResponse, Long investAmount) {
        return new ItemResponse(
                itemResponse.getId(),
                itemResponse.getTitle(),
                itemResponse.getDescription(),
                itemResponse.getAmount(),
                itemResponse.getRoomId(),
                itemResponse.getOwnerId(),
                investAmount);
    }
}
