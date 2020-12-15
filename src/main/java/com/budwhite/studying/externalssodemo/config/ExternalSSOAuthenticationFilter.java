package com.budwhite.studying.externalssodemo.config;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Component
public class ExternalSSOAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ExternalSSOAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        LOGGER.info("doFilterInternal got triggered");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            LOGGER.info("authentication is null, extracting data from the request");
            String username = httpServletRequest.getParameter("username");
            for(Map.Entry<String, String[]> item : httpServletRequest.getParameterMap().entrySet()) {
                LOGGER.info("dump: {} - {}", item.getKey(), item.getValue());
            }
            if(username!=null) {
                LOGGER.info("Authenticated {}", username);
                SecurityContextHolder.getContext().setAuthentication(new ExternalSSOAuthentication(new UserInformation(username)));
            }
            else {
                LOGGER.error("Bro..");
                SecurityContextHolder.getContext().setAuthentication(new ExternalSSOAuthentication(new UserInformation("bro")));
                //throw new ServletException("Bro...");
            }

        }
        else {
            LOGGER.debug("authentication is not null");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
