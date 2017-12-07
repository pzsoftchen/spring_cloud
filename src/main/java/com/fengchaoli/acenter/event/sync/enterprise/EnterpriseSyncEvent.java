package com.fengchaoli.acenter.event.sync.enterprise;

import com.fengchaoli.acenter.model.Enterprise;
import com.fengchaoli.acenter.model.User;
import com.sun.tools.javac.comp.Enter;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class EnterpriseSyncEvent extends ApplicationEvent {

    private String clientId;

    /**
     * Create a new ApplicationEvent.
     *
     */
    public EnterpriseSyncEvent(Enterprise enterprise, String clientId) {
        super(enterprise);
        this.clientId = clientId;
    }
}
