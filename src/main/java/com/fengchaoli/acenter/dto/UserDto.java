package com.fengchaoli.acenter.dto;

import com.fengchaoli.acenter.form.BaseForm;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto{

    private String id;

    private String account;

    private String password;

}
