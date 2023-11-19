package gcs.moi.dto.response;

import gcs.moi.domain.Invest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InvestResponse {

    private Long id;
    private Long investor;
    private Long itemId;
    private Long amount;

    public static InvestResponse from(Invest invest) {
        return new InvestResponse(
                invest.getId(),
                invest.getInvestor().getId(),
                invest.getItem().getId(),
                invest.getAmount()
                );
    }
}
