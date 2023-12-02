package gcs.moi.config.filter;

import gcs.moi.auth.web.AuthenticatedMember;
import gcs.moi.auth.web.SessionProvider;
import gcs.moi.domain.Member;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthFilter implements Filter {

    private final SessionProvider sessionProvider;
    private final AuthenticatedMember authenticatedMember;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        boolean isMatchedURI = Arrays.asList("/api/v1/member/join", "/api/v1/member/login")
                .stream()
                .anyMatch(uri -> uri.equals(requestURI));

        if (!isMatchedURI) {
            Member member = sessionProvider.getMemberBySessionId(httpRequest.getSession().getId());
            authenticatedMember.setMember(member);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        this.authenticatedMember.remove();
    }
}
