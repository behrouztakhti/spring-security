package com.behrouztakhti.security.config.jwt;


import com.behrouztakhti.security.domain.User;

/**
 * This interface has some methods in terms of jwtToken.
 * I've customized it.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 * @see JwtAuthenticationFilter
 */
public interface JwtService {

    String extractUsername(String token);
    Boolean isTokenValid(String token);
    String generateToken(User user);
}
