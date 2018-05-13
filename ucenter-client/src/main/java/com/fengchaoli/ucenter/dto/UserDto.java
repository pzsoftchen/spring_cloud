package com.fengchaoli.ucenter.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto{

    private String id;

    private String account;

    private String password;

    private List<UserMetaDto> userMetas = new ArrayList<>();
}
