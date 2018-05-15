package com.fengchaoli.ucenter.controller;

import com.fengchaoli.basic.security.SecurityUser;
import com.fengchaoli.ucenter.client.UserClient;
import com.fengchaoli.ucenter.dto.UserDTO;
import com.fengchaoli.ucenter.model.User;
import com.fengchaoli.ucenter.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class UserController implements UserClient {

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected UserService userService;

    @Override
    @GetMapping("/current")
    public Principal getUser(Principal principal) {
        return principal;
    }
}
