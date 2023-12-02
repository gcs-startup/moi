package gcs.moi.dto.response;

import gcs.moi.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {

    private Long id;
    private String name;

    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName()
                );
    }
}
