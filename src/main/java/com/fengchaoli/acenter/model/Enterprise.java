package com.fengchaoli.acenter.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
public class Enterprise extends BaseModel{
    @Column
    private String account;

    @Column
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "enterpriseId", referencedColumnName = "id")
    private List<EnterpriseMeta> enterpriseMetas = new ArrayList<>();

}
