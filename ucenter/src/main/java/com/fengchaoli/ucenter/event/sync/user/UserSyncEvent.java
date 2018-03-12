package com.fengchaoli.ucenter.event.sync.user;

import com.fengchaoli.ucenter.model.User;
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
