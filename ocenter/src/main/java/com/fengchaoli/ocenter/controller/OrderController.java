package com.fengchaoli.ocenter.controller;


import com.fengchaoli.basic.security.SecurityUser;
import com.fengchaoli.ocenter.dto.OrderItemDTO;
import com.fengchaoli.ocenter.statemachine.OrderStateMachineEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
//import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;


@RestController
public class OrderController {

    @Autowired
    private OrderStateMachineEngine orderStateMachineEngine;

    @GetMapping("/create")
    @ResponseBody
    public OrderItemDTO create() {
        return orderStateMachineEngine.create().toDTO(OrderItemDTO.class);
    }

    @GetMapping("/pay")
    @ResponseBody
    public OrderItemDTO pay(String orderId,Double amount) {
        return orderStateMachineEngine.pay(orderId).toDTO(OrderItemDTO.class);
    }

    @GetMapping("/current")
    public Principal getUser(Principal principal){
        return principal;
    }
}
