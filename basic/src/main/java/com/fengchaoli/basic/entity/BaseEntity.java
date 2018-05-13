package com.fengchaoli.basic.entity;

import com.fengchaoli.basic.dto.BaseDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.beans.Transient;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Persistable<String>,IEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column
    private String id;

    @Transient
    public boolean isNew() {
        return null == getId();
    }


    public <M extends BaseDTO> M toDTO(Class<M> dtoClass){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this,dtoClass);
    }
}
