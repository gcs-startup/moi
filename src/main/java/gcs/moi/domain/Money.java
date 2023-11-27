package gcs.moi.domain;

import gcs.moi.config.exception.ErrorCode;
import gcs.moi.config.exception.MoiApplicationException;
import jakarta.persistence.*;

@Entity
public class Money extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private Long amount;

    public void change(long amount) {
        if (this.amount + amount < 0) {
            throw new MoiApplicationException(ErrorCode.MONEY_NOT_SUFFICIENT);
        }
        this.amount += amount;
    }

}
