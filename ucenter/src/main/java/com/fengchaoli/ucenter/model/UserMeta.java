package com.fengchaoli.ucenter.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class UserMeta extends BaseModel{

    @Column
    private String userId;

    @Column
    private String clientId;

    @Column
    @Lob
    private String extra;

}
