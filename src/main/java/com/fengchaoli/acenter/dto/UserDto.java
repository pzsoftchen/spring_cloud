package com.fengchaoli.acenter.dto;

import com.fengchaoli.acenter.form.BaseForm;
import com.fengchaoli.acenter.model.Enterprise;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto{

    private String id;

    private String account;

    private String password;

    private String enterpriseId;

    private List<UserMetaDto> userMetas = new ArrayList<>();

    private EnterpriseDto enterpriseDto;
}
