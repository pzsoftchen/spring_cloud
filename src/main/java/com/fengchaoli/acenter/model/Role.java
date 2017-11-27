package com.fengchaoli.acenter.model;

import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Role extends BaseModel {

    @Column
    private String name;

    @Column
    private String parentId;
}
