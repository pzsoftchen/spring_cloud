package com.fengchaoli.basic.oauth2.feign;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(OAuth2ClientFeignConfiguration.class)
public @interface EnableOAuth2ClientFeign {
}
