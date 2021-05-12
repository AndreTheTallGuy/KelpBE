package com.Kelp2.kelp.filters;

import com.google.firebase.auth.AuthErrorCode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = "/**")
public class AuthorizationFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        List<String> response = Arrays.asList(req.getServletPath().replace("/", ":").split(":"));
        if (req.getMethod().equals("POST") || req.getMethod().equals("PUT")) {

            try {
                // Verify the ID token while checking if the token is revoked by passing checkRevoked
                // as true.
                boolean checkRevoked = true;
                FirebaseToken decodedToken = FirebaseAuth.getInstance()
                        .verifyIdToken((response.get(response.size() - 1)), checkRevoked);
                // Token is valid and not revoked.
                String uid = decodedToken.getUid();
            } catch (FirebaseAuthException e) {
                if (e.getAuthErrorCode() == AuthErrorCode.REVOKED_ID_TOKEN) {
                    // Token has been revoked. Inform the user to re-authenticate or signOut() the user.
                } else {

                }
            }
        }
    }
}

