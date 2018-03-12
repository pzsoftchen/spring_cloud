package com.fengchaoli.ucenter.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class EnterpriseMeta extends BaseModel{

    @Column
    private String enterpriseId;

    @Column
    private String clientId;

    @Column
    @Lob
    private String extra;

}
