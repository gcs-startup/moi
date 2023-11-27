package gcs.moi.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Invest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member investor;

    private Long amount;

    private Invest(Item item, Member investor, Long amount) {
        this.item = item;
        this.investor = investor;
        this.amount = amount;
    }

    public static Invest of(Item item, Member investor, Long amount) {
        return new Invest(item, investor, amount);
    }
}
