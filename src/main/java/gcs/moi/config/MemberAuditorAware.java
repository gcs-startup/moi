package gcs.moi.config;

import gcs.moi.auth.web.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberAuditorAware implements AuditorAware<String> {

    private final AuthenticatedMember authenticatedMember;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(authenticatedMember.orElseDefault().getName());
    }
}
