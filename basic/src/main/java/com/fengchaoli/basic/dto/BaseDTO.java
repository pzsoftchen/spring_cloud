package com.fengchaoli.basic.dto;

import com.fengchaoli.basic.entity.IEntity;
import lombok.Data;

@Data
public abstract class BaseDTO implements IEntity {

    private String id;

}
