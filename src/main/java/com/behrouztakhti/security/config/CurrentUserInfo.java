package com.behrouztakhti.security.config;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * This class is responsible for providing the application current user.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */
public interface CurrentUserInfo {

    default SecurityUser getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken){
            return null;
        }
        return (SecurityUser)authentication.getPrincipal();
    }
}
