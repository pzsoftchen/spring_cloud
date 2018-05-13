package com.fengchaoli.ocenter.statemachine;

import com.fengchaoli.ocenter.entity.OrderItem;
import com.fengchaoli.ocenter.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStateMachineEngine extends AbstractStateMachineEngine<OrderStateMachine> {


    @Autowired
    private OrderService orderService;

    public OrderItem create(){
        OrderItem order = new OrderItem();
        super.startAndFire(OrderState.CREATE_INIT,OrderEvent.CREATE,order);
        return order;
    }

    public OrderItem pay(String orderId){
        OrderItem order = orderService.getOne(orderId);
        super.fire(OrderEvent.PAY,order);
        return order;
    }
}
