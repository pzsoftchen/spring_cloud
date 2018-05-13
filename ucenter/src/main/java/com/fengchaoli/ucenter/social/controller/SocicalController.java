package com.fengchaoli.ucenter.social.controller;

import com.fengchaoli.ucenter.service.UserService;
import com.fengchaoli.ucenter.social.SocialUserInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
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
