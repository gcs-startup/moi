package gcs.moi.auth.web;

import gcs.moi.config.exception.ErrorCode;
import gcs.moi.config.exception.MoiApplicationException;
import gcs.moi.domain.Member;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthenticatedMember {

    private ThreadLocal<Member> member = new ThreadLocal<>();

    public Member get() {
        Member member = this.member.get();
        if (Objects.isNull(member)) {
            throw new MoiApplicationException(ErrorCode.MEMBER_NOT_FOUND);
        }
        return member;
    }

    public void setMember(Member member) {
        this.member.set(member);
    }

    public void remove() {
        this.member.remove();
    }

}
