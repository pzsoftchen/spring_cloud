package com.fengchaoli.acenter.event.register;

import com.fengchaoli.acenter.model.User;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class UserSyncEvent extends ApplicationEvent {

    private String clientId;

    /**
     * Create a new ApplicationEvent.
     *
     */
    public UserSyncEvent(User user,String clientId) {
        super(user);
        this.clientId = clientId;
    }
}
