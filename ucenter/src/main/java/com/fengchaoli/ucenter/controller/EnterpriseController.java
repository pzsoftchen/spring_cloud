package com.fengchaoli.ucenter.controller;

import com.fengchaoli.ucenter.dto.EnterpriseDto;
import com.fengchaoli.ucenter.form.EnterpriseForm;
import com.fengchaoli.ucenter.model.Enterprise;
import com.fengchaoli.ucenter.service.EnterpriseService;
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
