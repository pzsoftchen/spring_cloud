package com.fengchaoli.acenter.event.sync.enterprise;

import com.alibaba.fastjson.JSON;
import com.fengchaoli.acenter.dto.EnterpriseDto;
import com.fengchaoli.acenter.model.Enterprise;
import com.fengchaoli.acenter.model.User;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.json.JSONUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Data
@Component
public class EnterpriseSyncIndListener implements SmartApplicationListener
{
    @Value("${notify.url.ind}")
    private String url;


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
        return aClass == EnterpriseSyncEvent.class;
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
        EnterpriseSyncEvent enterpriseSyncEvent = (EnterpriseSyncEvent) applicationEvent;
        //获取注册用户对象信息
        Enterprise enterprise = (Enterprise) enterpriseSyncEvent.getSource();
        EnterpriseDto enterpriseDto = modelMapper.map(enterprise,EnterpriseDto.class);
        String clientId = enterpriseSyncEvent.getClientId();
        log.info("企业："+enterprise.getName()+"，注册成功，发送notify通知。clientId:"+clientId+"。url:"+url);
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("data", JSON.toJSONString(enterpriseDto));
        paramMap.put("clientId",clientId);
        paramMap.put("event","EnterpriseSyncEvent");
        HttpUtil.post(url, paramMap);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}