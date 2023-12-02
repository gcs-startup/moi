package gcs.moi.config.filter;

import gcs.moi.auth.web.AuthenticatedMember;
import gcs.moi.auth.web.SessionProvider;
import gcs.moi.config.exception.MoiApplicationException;
import gcs.moi.domain.Member;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthFilter implements Filter {

    private final SessionProvider sessionProvider;
    private final AuthenticatedMember authenticatedMember;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        String[] permitAll = {"/swagger-ui", "/v3/api-docs", "/api/v1/member/join", "/api/v1/member/login" };
        boolean isMatchedURI = Stream.of(permitAll)
                                     .anyMatch(requestURI::startsWith);
        if (!isMatchedURI) {
            Member member;
            try {
                member = sessionProvider.getMemberBySessionId(httpRequest.getSession().getId());
                authenticatedMember.setMember(member);
            } catch (MoiApplicationException e) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        this.authenticatedMember.remove();
    }
}
