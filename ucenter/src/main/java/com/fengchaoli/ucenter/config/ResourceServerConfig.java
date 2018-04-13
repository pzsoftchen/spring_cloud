package com.fengchaoli.ucenter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private SpringSocialConfigurer merryyouSpringSocialConfigurer;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.apply(merryyouSpringSocialConfigurer).and().authorizeRequests()
                .antMatchers("/v2/api-docs", "/app/token", "/sms/token", "/socialRegister").permitAll()
                .and().authorizeRequests().anyRequest().authenticated();
    }
}
