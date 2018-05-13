package com.fengchaoli.ucenter.dto;

import com.fengchaoli.basic.dto.BaseDTO;
import lombok.Data;

@Data
public class UserMetaDTO extends BaseDTO {
    private String clientId;

    private String extra;
}
