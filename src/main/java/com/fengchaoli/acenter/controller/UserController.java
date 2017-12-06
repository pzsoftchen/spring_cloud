package com.fengchaoli.acenter.controller;

import com.fengchaoli.acenter.form.UserForm;
import com.fengchaoli.acenter.model.SecurityUser;
import com.fengchaoli.acenter.model.User;
import com.fengchaoli.acenter.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.security.Principal;


@RestController
public class UserController {

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected UserService userService;

    @GetMapping("/api/currentUser")
    public User user(OAuth2Authentication user) {
        return ((SecurityUser)user.getPrincipal()).getUser();
    }

    @PostMapping("/api/currentUser/{id}")
    public User save(@PathVariable("id")String id, UserForm userForm,OAuth2Authentication user) {
        String client = user.getOAuth2Request().getClientId();
        return userService.save(userForm,client);
    }
}
