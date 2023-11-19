package gcs.moi.service;

import gcs.moi.domain.Member;
import gcs.moi.dto.request.MemberRequest;
import gcs.moi.dto.response.MemberResponse;
import gcs.moi.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse save(MemberRequest memberRequest) {
        Member member = memberRepository.save(Member.of(
                memberRequest.getToken(),
                memberRequest.getName()));
        return MemberResponse.from(member);
    }

}
