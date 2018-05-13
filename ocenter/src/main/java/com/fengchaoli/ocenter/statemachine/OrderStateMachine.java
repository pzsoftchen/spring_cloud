package com.fengchaoli.ocenter.statemachine;

import com.fengchaoli.ocenter.entity.OrderItem;
import com.fengchaoli.ocenter.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.squirrelframework.foundation.fsm.annotation.*;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

@Configurable(preConstruction=true)
@StateMachineParameters(stateType=OrderState.class, eventType=OrderEvent.class, contextType=OrderItem.class)
@States({
        @State(name="CREATE_INIT",initialState=true),
        @State(name="CREATE_DONE"),
        @State(name="PAY_DONE"),
        @State(name="CONFIRM_DONE"),
        @State(name="SHIP_DONE"),
        @State(name="SETTLE_DONE"),
        @State(name="CLOSE_DONE")
})
@Transitions({
        @Transit(from="CREATE_INIT", to="CREATE_DONE", on="CREATE",callMethod="create"),
        @Transit(from="CREATE_DONE", to="PAY_DONE", on="PAY",callMethod="pay")
})
public class OrderStateMachine extends AbstractUntypedStateMachine {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private OrderService orderService;

    public void create(OrderState from, OrderState to, OrderEvent event, OrderItem order) {
        order.setState(to);
        orderService.save(order);
    }

    public void pay(OrderState from, OrderState to, OrderEvent event, OrderItem order) {
        order.setState(to);
        orderService.save(order);
    }
}
