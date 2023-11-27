package gcs.moi.service;

import gcs.moi.config.exception.ErrorCode;
import gcs.moi.config.exception.MoiApplicationException;
import gcs.moi.domain.Member;
import gcs.moi.dto.request.MemberRequest;
import gcs.moi.dto.response.MemberResponse;
import gcs.moi.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

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

}
