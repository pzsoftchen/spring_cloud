package com.fengchaoli.gateway.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto{

    private String id;

    private String account;

    private String password;

    private String enterpriseId;

    private List<UserMetaDto> userMetas = new ArrayList<>();

    private EnterpriseDto enterpriseDto;
}
