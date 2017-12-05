package com.fengchaoli.acenter.form;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserForm extends BaseForm {

    private String id;

    @NotNull
    private String account;

    @NotNull
    private String password;

    @NotNull
    private String extra;
}
