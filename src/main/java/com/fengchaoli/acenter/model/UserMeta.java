package com.fengchaoli.acenter.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class UserMeta extends BaseModel{

    @OneToOne
    private User user;

    @Column
    private String clientId;

    @Column
    @Lob
    private String extra;

}