package com.fengchaoli.ucenter.event.sync.user;

import com.alibaba.fastjson.JSON;
import com.fengchaoli.ucenter.dto.EnterpriseDto;
import com.fengchaoli.ucenter.dto.UserDto;
import com.fengchaoli.ucenter.model.Enterprise;
import com.fengchaoli.ucenter.model.User;
import com.fengchaoli.ucenter.service.EnterpriseService;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Data
@Component
public class UserSyncIndListener implements SmartApplicationListener
{
    @Value("${notify.url.ind}")
    private String url;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    protected ModelMapper modelMapper;


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
        return true;
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

        UserDto userDto = modelMapper.map(user,UserDto.class);
        if(StrUtil.isNotBlank(user.getEnterpriseId())){
            Enterprise enterprise = enterpriseService.getOne(user.getEnterpriseId());
            userDto.setEnterpriseDto(modelMapper.map(enterprise, EnterpriseDto.class));
        }

        log.info("用户："+user.getAccount()+"，注册成功，发送notify通知。clientId:"+clientId+"。url:"+url);
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("data",  JSON.toJSONString(userDto));
        paramMap.put("clientId",clientId);
        paramMap.put("event","UserSyncEvent");
        HttpUtil.post(url, paramMap);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
