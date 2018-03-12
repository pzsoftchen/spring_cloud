package com.fengchaoli.gateway.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NotifyForm extends BaseForm {
    @NotNull
    private String userId;
    @NotNull
    private String account;

    @NotNull
    private String password;

    @NotNull
    private String enterpriseId;

    @NotNull
    private String enterpriseName;
}
