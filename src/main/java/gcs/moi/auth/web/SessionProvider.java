package gcs.moi.auth.web;

import gcs.moi.config.exception.ErrorCode;
import gcs.moi.config.exception.MoiApplicationException;
import gcs.moi.domain.Member;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionProvider {

    private final Map<String, Member> sessions = new ConcurrentHashMap<>();  //sessionId, member
    private final Map<String, String> members = new ConcurrentHashMap<>();  //token, sessionId

    public void setMemberSession(String sessionId, Member member) {
        this.sessions.putIfAbsent(sessionId, member);
        this.members.compute(member.getToken(), (key, oldValue) -> {
            if (!Objects.isNull(oldValue) && !sessionId.equals(oldValue)) {
                this.sessions.remove(oldValue);
            }
            return sessionId;
        });
    }


    public Member getMemberBySessionId(String sessionId) {
        Member member = this.sessions.get(sessionId);
        if (Objects.isNull(member)) {
            throw new MoiApplicationException(ErrorCode.UNAUTHORIZED_BEHAVIOR);
        }
        return member;
    }
}
