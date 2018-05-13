package com.fengchaoli.ucenter.form;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class BaseForm implements Serializable {


}
