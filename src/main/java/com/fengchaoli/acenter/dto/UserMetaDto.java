package com.fengchaoli.acenter.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;

@Data
public class UserMetaDto {
    private String clientId;

    private String extra;
}
