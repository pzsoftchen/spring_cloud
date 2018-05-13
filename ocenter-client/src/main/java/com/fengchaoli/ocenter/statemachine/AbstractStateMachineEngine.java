package com.fengchaoli.ocenter.statemachine;

import com.fengchaoli.basic.entity.IEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.StateMachineConfiguration;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;

import java.util.concurrent.ConcurrentHashMap;


public abstract class AbstractStateMachineEngine<T extends UntypedStateMachine>{
    private ConcurrentHashMap<String,T> machineMap = new ConcurrentHashMap<String,T>();

    protected UntypedStateMachineBuilder stateMachineBuilder = null;

    protected StateMachineConfiguration stateMachineConfiguration = null;

    @Autowired
    protected ApplicationContext applicationContext;
    @SuppressWarnings("unchecked")
    public AbstractStateMachineEngine() {
        //识别泛型参数
        Class<T> genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(),
                AbstractStateMachineEngine.class);
        stateMachineBuilder = StateMachineBuilderFactory.create(genericType, ApplicationContext.class);
        //暂时开启debug进行日志trace
        stateMachineConfiguration = StateMachineConfiguration.create().enableDebugMode(true).enableAutoStart(true);
    }


    public T startAndFire(Object initialStateId,Object trigger, IEntity context) {
        T stateMachine = stateMachineBuilder.newUntypedStateMachine(
                initialStateId, stateMachineConfiguration);
        stateMachine.fire(trigger, context);

        machineMap.put(context.getId(),stateMachine);

        return stateMachine;
    }

    //delegate fire
    public T fire(Object trigger, IEntity context) {
        T stateMachine = machineMap.get(context.getId());
        stateMachine.fire(trigger, context);

        return stateMachine;
    }
}
