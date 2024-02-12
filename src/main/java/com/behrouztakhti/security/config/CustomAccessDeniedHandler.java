package com.behrouztakhti.security.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import java.io.IOException;


/**
 * AccessDeniedHandler handles authorisation exception.
 * I've customized it.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 * @see SecurityConfig
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * this method handles authorization exception.
     * @param  request HttpServletRequest.
     * @param  response HttpServletResponse.
     * @param  accessDeniedException AccessDeniedException.
     * @see CustomAccessDeniedHandler
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "forbidden !!!");
    }
}
