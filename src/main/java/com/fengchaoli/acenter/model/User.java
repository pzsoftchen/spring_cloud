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


    @OneToMany(fetch = FetchType.EAGER)
    private List<UserMeta> userMetas = new ArrayList<>();

}
