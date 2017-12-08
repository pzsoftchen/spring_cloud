package com.fengchaoli.acenter.controller;

import com.fengchaoli.acenter.dto.EnterpriseDto;
import com.fengchaoli.acenter.dto.UserDto;
import com.fengchaoli.acenter.form.EnterpriseForm;
import com.fengchaoli.acenter.form.UserForm;
import com.fengchaoli.acenter.model.Enterprise;
import com.fengchaoli.acenter.model.SecurityUser;
import com.fengchaoli.acenter.model.User;
import com.fengchaoli.acenter.service.EnterpriseService;
import com.fengchaoli.acenter.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
public class EnterpriseController {

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected EnterpriseService enterpriseService;

    @PostMapping("/api/enterprises/sync")
    public EnterpriseDto insert(EnterpriseForm enterpriseForm, OAuth2Authentication currentUser) {
        String client = currentUser.getOAuth2Request().getClientId();
        Enterprise enterprise = enterpriseService.insert(null,enterpriseForm,client);
        return modelMapper.map(enterprise, EnterpriseDto.class);
    }


    @PutMapping("/api/enterprises/sync/{id}")
    public EnterpriseDto udpate(@PathVariable("id")String id, EnterpriseForm enterpriseForm,OAuth2Authentication currentUser) {
        String client = currentUser.getOAuth2Request().getClientId();
        Enterprise enterprise = enterpriseService.update(id,enterpriseForm,client);
        return modelMapper.map(enterprise, EnterpriseDto.class);
    }
}
