package com.fengchaoli.ucenter.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class BaseModel implements Persistable<String> {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column
    private String id;

    @Transient
    public boolean isNew() {
        return null == getId();
    }
}
