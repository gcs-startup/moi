package gcs.moi.auth.web;

import gcs.moi.config.exception.ErrorCode;
import gcs.moi.config.exception.MoiApplicationException;
import gcs.moi.domain.Member;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Supplier;

@Component
public class AuthenticatedMember {

    private ThreadLocal<Member> member = new ThreadLocal<>();

    public Member orElseDefault() {
        Member member = this.member.get();
        if (Objects.isNull(member)) {
            return Member.of("", "anonymous");
        }
        return member;
    }

    public Member orElseThrow() {
        Member member = this.member.get();
        if (Objects.isNull(member)) {
            throw new MoiApplicationException(ErrorCode.MEMBER_NOT_FOUND);
        }
        return member;
    }

    public <X extends Throwable> Member orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        Member member = this.member.get();
        if (Objects.isNull(member)) {
            throw exceptionSupplier.get();
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
