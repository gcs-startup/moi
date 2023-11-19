package gcs.moi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestRequest {

    private Long memberId;  //TODO authentication 구현 전 사용자 식별을 위한 임시값
    private Long itemId;
    private Long amount;
}
