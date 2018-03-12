package com.fengchaoli.ucenter.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserForm extends BaseForm {
    @NotNull
    private String account;

    @NotNull
    private String password;

    @NotNull
    private String enterpriseId;

    @NotNull
    private String extra;
}
