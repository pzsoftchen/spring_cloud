package com.fengchaoli.acenter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @PostMapping("/user")
    public Principal userTest(Principal user) {
        return user;
    }
}
