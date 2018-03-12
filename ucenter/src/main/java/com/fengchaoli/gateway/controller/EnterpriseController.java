package com.fengchaoli.gateway.controller;

import com.fengchaoli.gateway.dto.EnterpriseDto;
import com.fengchaoli.gateway.form.EnterpriseForm;
import com.fengchaoli.gateway.model.Enterprise;
import com.fengchaoli.gateway.service.EnterpriseService;
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
