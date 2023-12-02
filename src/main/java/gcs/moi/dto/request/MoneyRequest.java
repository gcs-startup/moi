package gcs.moi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyRequest {

    private Long roomId;
    private Long memberId;
    private Long amount;

}
