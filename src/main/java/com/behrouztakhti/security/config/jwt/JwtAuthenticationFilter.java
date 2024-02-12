package com.behrouztakhti.security.config.jwt;

import com.behrouztakhti.security.repository.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;


/**
 * This class extends OncePerRequestFilter and is responsible for catching every request and intercept it in order to check them.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private final JwtService jwtSrv;
    private final UserDetailsService userDetailsSrv;
    private TokenRepository tokenRepo;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService, TokenRepository tokenRepository) {
        this.jwtSrv = jwtService;
        this.userDetailsSrv = userDetailsService;
        this.tokenRepo = tokenRepository;
    }


    /**
     * this method is responsible for catching every request and intercept it in order to check them.
     * @param  request HttpServletRequest.
     * @param  response HttpServletResponse.
     * @param  filterChain FilterChain.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/auth")){
            filterChain.doFilter(request, response);
            return;
        }
        final Optional<String> jwt = getJwtFromRequest(request);
        jwt.ifPresent(token -> {
            var isTokenExist = tokenRepo.findByToken(token).isPresent();
                if (isTokenExist && jwtSrv.isTokenValid(token)) {
                    final String username = jwtSrv.extractUsername(token);
                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                        UserDetails userDetails = this.userDetailsSrv.loadUserByUsername(username);
                        setSecurityContext(new WebAuthenticationDetailsSource().buildDetails(request), userDetails);
                    }
                }
        });
        filterChain.doFilter(request, response);
    }

    private static Optional<String> getJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)){
            return Optional.of(bearerToken.substring(7));
        }
        return Optional.empty();
    }

    private void setSecurityContext(WebAuthenticationDetails authDetails, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(authDetails);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

}
