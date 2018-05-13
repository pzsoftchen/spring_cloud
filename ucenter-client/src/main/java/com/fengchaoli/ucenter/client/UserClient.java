package com.fengchaoli.ucenter.client;

import com.fengchaoli.ucenter.dto.UserDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(serviceId = "ucenter")
public interface UserClient {

    @GetMapping("/api/users/current")
    UserDto user(OAuth2Authentication currentUser);

}