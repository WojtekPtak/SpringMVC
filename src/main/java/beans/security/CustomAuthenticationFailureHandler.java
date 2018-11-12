package beans.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    public static final Logger log = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        //TODO: add custom page
        /*
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (auth != null) {
            log.warn("User: " + auth.getName() + " has no access to: " + request.getRequestURI());
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.sendRedirect(request.getContextPath() + "/login_failure");
        */
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getOutputStream().println("CustomAuthenticationFailureHandler.onAuthenticationFailure called");
    }
}
