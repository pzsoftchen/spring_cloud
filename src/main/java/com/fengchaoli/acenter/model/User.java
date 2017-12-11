package com.fengchaoli.acenter.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.AbstractPersistable;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<UserMeta> userMetas = new ArrayList<>();
}
