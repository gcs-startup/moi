package gcs.moi.dto.invest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberInvest {

    private Long memberId;
    private Long roomId;
    private Long itemId;
    private Long investAmount;

}
