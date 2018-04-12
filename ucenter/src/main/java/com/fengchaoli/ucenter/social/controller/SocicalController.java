package com.fengchaoli.ucenter.social.controller;

import com.fengchaoli.ucenter.dto.UserDto;
import com.fengchaoli.ucenter.form.NotifyForm;
import com.fengchaoli.ucenter.form.UserForm;
import com.fengchaoli.ucenter.model.SecurityUser;
import com.fengchaoli.ucenter.model.User;
import com.fengchaoli.ucenter.service.UserService;
import com.fengchaoli.ucenter.social.SocialUserInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;


@RestController
public class SocicalController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected UserService userService;

    /**
     * 授权后的信息
     *
     * @param map
     * @return
     */
    @GetMapping(value = "/socialRegister")
    @ResponseBody
    public SocialUserInfo socialRegister(HttpServletRequest request, Map<String, Object> map) {
        SocialUserInfo userInfo = new SocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadImg(connection.getImageUrl());

        return userInfo;
//        map.put("user", userInfo);
//        return new ModelAndView("socialRegister", map);
    }
}
