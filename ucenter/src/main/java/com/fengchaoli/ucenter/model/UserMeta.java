package com.fengchaoli.ucenter.model;

import com.fengchaoli.basic.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table
@Data
public class UserMeta extends BaseEntity {

    @Column
    private String userId;

    @Column
    private String clientId;

    @Column
    @Lob
    private String extra;

}
