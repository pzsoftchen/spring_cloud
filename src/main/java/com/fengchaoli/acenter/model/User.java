package com.fengchaoli.acenter.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class User extends BaseModel{
    @Column
    private String email;

    @Column
    private String password;

    @Column
    @Lob
    private String extra;

}
