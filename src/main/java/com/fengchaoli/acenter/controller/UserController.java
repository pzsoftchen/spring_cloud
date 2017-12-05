package com.fengchaoli.acenter.controller;

import com.fengchaoli.acenter.form.UserForm;
import com.fengchaoli.acenter.model.SecurityUser;
import com.fengchaoli.acenter.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;


@RestController
public class UserController {

    @Autowired
    protected ModelMapper modelMapper;

    @GetMapping("/api/currentUser")
    public User user(OAuth2Authentication user) {
        return ((SecurityUser)user.getPrincipal()).getUser();
    }
}
