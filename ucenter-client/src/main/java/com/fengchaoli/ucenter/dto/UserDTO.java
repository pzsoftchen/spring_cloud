package com.fengchaoli.ucenter.dto;

import com.fengchaoli.basic.dto.BaseDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO extends BaseDTO {

    private String account;

    private String password;

    private String enterpriseId;

    private List<UserMetaDTO> userMetas = new ArrayList<>();
}
