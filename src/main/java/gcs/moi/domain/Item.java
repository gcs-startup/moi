package gcs.moi.domain;

import gcs.moi.config.exception.ErrorCode;
import gcs.moi.config.exception.MoiApplicationException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Long amount;
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member owner;

    private Item(String title, String description, Long amount, boolean isDeleted, Room room, Member owner) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.isDeleted = isDeleted;
        this.room = room;
        this.owner = owner;
    }

    public static Item of(String title, String description, Long amount, boolean isDeleted, Room room, Member owner) {
        return new Item(title, description, amount, isDeleted, room, owner);
    }

    public void change(long amount) {
        if (this.amount + amount < 0) {
            throw new MoiApplicationException(ErrorCode.ITEM_AMOUNT_NOT_VALID);
        }
        this.amount += amount;
    }
}
