package gcs.moi.dto.response;

import gcs.moi.domain.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MoneySaveResponse {

    private Long id;
    private Long roomId;
    private Long memberId;
    private Long amount;

    public static MoneySaveResponse from(Money money) {
        return new MoneySaveResponse(
                money.getId(),
                money.getRoom().getId(),
                money.getMember().getId(),
                money.getAmount());
    }
}
