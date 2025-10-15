package com.mamaalert.security;

import com.mamaalert.data.model.User;
import com.mamaalert.services.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * Filter that validates JWTs and sets the authenticated user into the SecurityContext.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final AuthService authService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, @Lazy AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

    /**
     * Skip the filter for public auth endpoints (login and initial superadmin registration).
     * Adjust paths as needed for your app.
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        // allow login and the very first superadmin registration without a token
        return path.equalsIgnoreCase("/auth/login")
                || path.equalsIgnoreCase("/auth/register/superadmin");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            String authHeader = request.getHeader("Authorization");
            String token = null;
            String email = null;

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                email = jwtUtil.extractEmail(token); // may return null if token malformed
            }

            // only proceed if we have an email and there's no existing authentication
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // load user (your service returns a User which contains role)
                User user = authService.findUserByEmail(email);

                // validate token (signature, expiration, subject, etc.)
                if (token != null && jwtUtil.validateToken(token, user.getEmail())) {

                    // build authority with ROLE_ prefix so hasRole() / @PreAuthorize works
                    SimpleGrantedAuthority authority =
                            new SimpleGrantedAuthority("ROLE_" + user.getRole().name());

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    user,
                                    null,
                                    Collections.singletonList(authority)
                            );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception ex) {
            // Important: do not stop the filter chain — just clear authentication and continue.
            // Logging is helpful while debugging (use your logger if available).
            logger.debug("JWT authentication failed: " + ex.getMessage());
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
