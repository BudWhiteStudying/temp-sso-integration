package com.budwhite.studying.externalssodemo.config;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ExternalSSOAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ExternalSSOAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.debug("Reached authenticate method, authentication is {}, anyway I'm returning it", authentication!=null ? "not null" : "null");
        return authentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
