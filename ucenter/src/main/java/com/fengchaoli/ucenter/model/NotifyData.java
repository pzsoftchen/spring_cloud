package com.fengchaoli.ucenter.model;

import com.fengchaoli.basic.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
@Data
public class NotifyData extends BaseEntity {
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
