package com.behrouztakhti.security.config;

import com.behrouztakhti.security.config.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final LogoutHandler logoutHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    public SecurityConfig(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter,
                          LogoutHandler logoutHandler, AuthenticationEntryPoint authenticationEntryPoint,
                          AccessDeniedHandler accessDeniedHandler) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthFilter = jwtAuthenticationFilter;
        this.logoutHandler = logoutHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    private static final AntPathRequestMatcher[] WHITELIST = {
            AntPathRequestMatcher.antMatcher("/auth/**"),
            AntPathRequestMatcher.antMatcher("/v2/api-docs"),
            AntPathRequestMatcher.antMatcher("/v3/api-docs"),
            AntPathRequestMatcher.antMatcher("/v3/api-docs/**"),
            AntPathRequestMatcher.antMatcher("/swagger-resources"),
            AntPathRequestMatcher.antMatcher("/swagger-resources/**"),
            AntPathRequestMatcher.antMatcher("/configuration/ui"),
            AntPathRequestMatcher.antMatcher("/configuration/security"),
            AntPathRequestMatcher.antMatcher("/swagger-ui/**"),
            AntPathRequestMatcher.antMatcher("/webjars/**"),
            AntPathRequestMatcher.antMatcher("/swagger-ui.html")
    };


    /**
     * this method provides a filter that intercepts request and authenticate the user.
     * this also provides a mechanism to ignore some endpoints from security procedure.
     * @param  httpSecurity this extends AbstractConfiguredSecurityBuilder<DefaultSecurityFilterChain, HttpSecurity>
     * @return securityFilterChain
     * @see SecurityConfig
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        var managerRole = AuthorityAuthorizationManager.<RequestAuthorizationContext>hasRole("MANAGER");
        managerRole.setRoleHierarchy(roleHierarchy());
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITELIST).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/admin/**")).hasRole("ADMIN")
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/manager/**")).access(managerRole)
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutUrl("/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()))
                .exceptionHandling(exc -> exc
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS).maximumSessions(1)).build();
    }


    /**
     * Exposing a bean of type RoleHierarchy :
     * We’ve configured the role ADMIN to include the role MANAGER, which in turn includes the role USER.
     * @return hierarchy RoleHierarchy.
     * @see SecurityConfig
     */
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_ADMIN > ROLE_MANAGER");
        return hierarchy;
    }
}
