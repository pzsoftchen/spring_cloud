package com.fengchaoli.gateway.model;

import lombok.Data;

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
