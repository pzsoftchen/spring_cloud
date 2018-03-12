package com.fengchaoli.gateway.event.sync.enterprise;

import com.fengchaoli.gateway.model.Enterprise;
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
