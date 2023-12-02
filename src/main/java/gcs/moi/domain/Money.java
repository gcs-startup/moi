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
public class Money extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private Long amount;

    private Money(Room room, Member member, Long amount) {
        this.room = room;
        this.member = member;
        this.amount = amount;
    }

    public static Money of(Room room, Member member, Long amount) {
        return new Money(room, member, amount);
    }

    public void change(long amount) {
        if (this.amount + amount < 0) {
            throw new MoiApplicationException(ErrorCode.MONEY_NOT_SUFFICIENT);
        }
        this.amount += amount;
    }

}
