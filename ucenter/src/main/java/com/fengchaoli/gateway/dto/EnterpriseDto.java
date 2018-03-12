package com.fengchaoli.gateway.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EnterpriseDto {

    private String id;

    private String name;

    private List<EnterpriseMetaDto> enterpriseMetas = new ArrayList<>();

}
