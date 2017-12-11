package com.fengchaoli.acenter.controller;

import com.fengchaoli.acenter.dto.UserDto;
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

    @GetMapping("/api/users/current")
    public User user(OAuth2Authentication user) {
        String userId = ((SecurityUser)user.getPrincipal()).getUserId();
        return userService.getOne(userId);
    }

    @PostMapping("/api/users/sync")
    public UserDto insert(UserForm userForm,OAuth2Authentication currentUser) {
        String client = currentUser.getOAuth2Request().getClientId();
        User user = userService.insert(userForm,client);
        return modelMapper.map(user, UserDto.class);
    }

    @PutMapping("/api/users/sync/{id}")
    public UserDto udpate(@PathVariable("id")String id, UserForm userForm,OAuth2Authentication currentUser) {
        String client = currentUser.getOAuth2Request().getClientId();
        User user = userService.update(id,userForm,client);
        return modelMapper.map(user, UserDto.class);
    }
}
