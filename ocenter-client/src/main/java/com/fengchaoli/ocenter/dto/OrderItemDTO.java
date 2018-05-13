package com.fengchaoli.ocenter.dto;

import com.fengchaoli.basic.dto.BaseDTO;
import com.fengchaoli.ocenter.statemachine.OrderState;
import lombok.Data;

@Data
public class OrderItemDTO extends BaseDTO {

    private OrderState state;
}
