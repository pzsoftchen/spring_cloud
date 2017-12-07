package com.fengchaoli.acenter.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EnterpriseForm extends BaseForm {
    @NotNull
    private String name;

    @NotNull
    private String extra;
}
