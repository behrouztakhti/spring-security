package com.behrouztakhti.security.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import java.io.IOException;


/**
 * AuthenticationEntryPoint handles authentication exception.
 * I've customized it.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 * @see SecurityConfig
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {


    /**
     * this method handles authentication exception.
     * @param  request HttpServletRequest.
     * @param  response HttpServletResponse.
     * @param  authException AuthenticationException.
     * @see CustomAuthenticationEntryPoint
     * @implNote As of Spring Boot 2.3,The error message and any binding errors are no longer included in the default error page by default.
        This reduces the risk of leaking information to a client.server.error.include-message and server.error.include-binding-errors
        can be used to control the inclusion of the message and binding errors respectively.
        Supported values are always, on-param, and never.
     */
    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response,
                         final AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized !!!");

    }
}
