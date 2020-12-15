package com.budwhite.studying.externalssodemo.config;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ExternalSSOSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ExternalSSOSecurityConfig.class);

    @Autowired
    private ExternalSSOAuthenticationFilter externalSSOAuthenticationFilter;

    @Autowired
    private ExternalSSOAuthenticationProvider externalSSOAuthenticationProvider;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authManagerBuilder) {
        LOGGER.info("Configuring the AuthenticationManager...");
        authManagerBuilder
                .authenticationProvider(externalSSOAuthenticationProvider);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
        //.authorizeRequests().antMatchers("*").permitAll()
        //.anyRequest().authenticated().and()
        .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

        httpSecurity.addFilterBefore(externalSSOAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        super.configure(httpSecurity);
    }
}
