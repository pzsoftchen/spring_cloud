package com.fengchaoli.acenter.event.register;

import com.fengchaoli.acenter.model.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
public class UserSyncLogListener implements SmartApplicationListener
{
    /**
     *  该方法返回true&supportsSourceType同样返回true时，才会调用该监听内的onApplicationEvent方法
     * @param aClass 接收到的监听事件类型
     * @return
     */
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        //只有UserRegisterEvent监听类型才会执行下面逻辑
        return aClass == UserSyncEvent.class;
    }

    /**
     *  该方法返回true&supportsEventType同样返回true时，才会调用该监听内的onApplicationEvent方法
     * @param aClass
     * @return
     */
    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        //只有在UserService内发布的UserRegisterEvent事件时才会执行下面逻辑
        return aClass == User.class;
    }

    /**
     *  supportsEventType & supportsSourceType 两个方法返回true时调用该方法执行业务逻辑
     * @param applicationEvent 具体监听实例，这里是UserRegisterEvent
     */
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        //转换事件类型
        UserSyncEvent userSyncEvent = (UserSyncEvent) applicationEvent;
        //获取注册用户对象信息
        User user = (User) userSyncEvent.getSource();
        String clientId = userSyncEvent.getClientId();
        System.out.println("用户："+user.getAccount()+"，注册成功，发送notify通知。clientId:"+clientId+"。url:");
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
