package com.fengchaoli.acenter.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
