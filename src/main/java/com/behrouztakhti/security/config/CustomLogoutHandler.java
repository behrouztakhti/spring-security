package com.behrouztakhti.security.config;

import com.behrouztakhti.security.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import java.util.Objects;


/**
 * LogoutHandler is responsible to sign out the user and clear context of SecurityContextHolder in order to remove user sessions.
 * I've customized and bind it to TokenRepository.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 * @see ApplicationConfig
 */
public class CustomLogoutHandler implements LogoutHandler {

    @Autowired
    TokenRepository tokenRepo;

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";


    /**
     * this method is responsible to sign out the user and clear context of SecurityContextHolder in order to remove user sessions.
     * @param  request HttpServletRequest.
     * @param  response HttpServletResponse.
     * @param  authentication Authentication.
     * @see CustomLogoutHandler
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String jwt;
        if (authHeader == null ||!authHeader.startsWith(BEARER)) {
            return;
        }
        jwt = authHeader.substring(7);
        var storedToken = tokenRepo.findByToken(jwt).orElse(null);
        if (Objects.nonNull(storedToken)){
            tokenRepo.delete(storedToken);
            SecurityContextHolder.clearContext();
        }
    }


}
