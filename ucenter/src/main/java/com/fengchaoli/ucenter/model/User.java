package com.fengchaoli.ucenter.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
public class User extends BaseModel{
    @Column
    private String account;

    @Column
    private String password;

    @Column
    private String enterpriseId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<UserMeta> userMetas = new ArrayList<>();
}
