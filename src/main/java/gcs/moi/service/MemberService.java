package gcs.moi.service;

import gcs.moi.auth.web.SessionProvider;
import gcs.moi.config.exception.ErrorCode;
import gcs.moi.config.exception.MoiApplicationException;
import gcs.moi.domain.Member;
import gcs.moi.dto.request.MemberRequest;
import gcs.moi.dto.response.MemberResponse;
import gcs.moi.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final SessionProvider sessionProvider;

    @Transactional
    public MemberResponse save(MemberRequest memberRequest) {
        Member member;
        try {
            member = memberRepository.save(Member.of(
                    memberRequest.getToken(),
                    memberRequest.getName()));
        } catch (DataIntegrityViolationException e) {
            throw new MoiApplicationException(ErrorCode.MEMBER_ALREADY_JOINED);
        }
        return MemberResponse.from(member);
    }

    public MemberResponse login(MemberRequest memberRequest, HttpServletRequest request) {
        Member member = findByTokenOrElseThrow(memberRequest.getToken());
        sessionProvider.setMemberSession(request.getSession().getId(), member);
        return MemberResponse.from(member);
    }

    public Member findByIdOrElseThrow(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MoiApplicationException(ErrorCode.MEMBER_NOT_FOUND));
    }

    private Member findByTokenOrElseThrow(String token) {
        return memberRepository.findMemberByToken(token)
                .orElseThrow(() -> new MoiApplicationException(ErrorCode.MEMBER_NOT_FOUND));
    }

}
