package com.fengchaoli.acenter.model;

import com.fengchaoli.acenter.form.BaseForm;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
@Data
public class NotifyData extends BaseModel {
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
