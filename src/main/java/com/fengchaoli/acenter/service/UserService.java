package com.fengchaoli.acenter.service;

import com.fengchaoli.acenter.dto.UserDto;
import com.fengchaoli.acenter.event.sync.enterprise.EnterpriseSyncEvent;
import com.fengchaoli.acenter.event.sync.user.UserSyncEvent;
import com.fengchaoli.acenter.form.NotifyForm;
import com.fengchaoli.acenter.form.UserForm;
import com.fengchaoli.acenter.model.*;
import com.fengchaoli.acenter.repository.EnterpriseRepository;
import com.fengchaoli.acenter.repository.NotifyDataRepository;
import com.fengchaoli.acenter.repository.UserRepository;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
@Slf4j
public class UserService {

    @Autowired
    private ApplicationEventMulticaster applicationEventMulticaster;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private NotifyDataRepository notifyDataRepository;

    public User getOne(String id) {
        return userRepository.findOne(id);
    }

    public User insert(UserForm userForm, String clientId) {
        //查询用户是否存在
        User user = userRepository.findFirstByAccount(userForm.getAccount());
        if (user == null)
            user = new User();
        user.setAccount(userForm.getAccount());
        user.setPassword(userForm.getPassword());
        user.setEnterpriseId(userForm.getEnterpriseId());

        UserMeta userMeta = user.getUserMetas().stream().filter(meta ->
                ObjectUtil.equal(clientId, meta.getClientId())).findFirst().orElseGet(() -> new UserMeta());
        userMeta.setClientId(clientId);
        userMeta.setExtra(userForm.getExtra());
        user.getUserMetas().add(userMeta);

        userRepository.save(user);
        applicationEventMulticaster.multicastEvent(new UserSyncEvent(user, clientId));
        return user;
    }

    public User update(String id, UserForm userForm, String clientId) {
        User user = userRepository.getOne(id);
        user.setAccount(userForm.getAccount());
        user.setPassword(userForm.getPassword());
        user.setEnterpriseId(userForm.getEnterpriseId());
        UserMeta userMeta = user.getUserMetas().stream().filter(meta ->
                ObjectUtil.equal(clientId, meta.getClientId())).findFirst().orElseGet(() -> new UserMeta());
        if (userMeta.getId() == null) {
            userMeta.setClientId(clientId);
            userMeta.setExtra(userForm.getExtra());
            user.getUserMetas().add(userMeta);
        } else {
            userMeta.setExtra(userForm.getExtra());
        }

        userRepository.save(user);
        applicationEventMulticaster.multicastEvent(new UserSyncEvent(user, clientId));
        return user;
    }

    public User notify(NotifyForm notifyForm, String clientId) {
        Enterprise enterprise = enterpriseRepository.findOne(notifyForm.getEnterpriseId());
        if (enterprise == null) {
            enterprise = new Enterprise();
            enterprise.setId(notifyForm.getEnterpriseId());
            enterprise.setName(notifyForm.getEnterpriseName());
            enterpriseRepository.save(enterprise);
            //发送企业信息
            applicationEventMulticaster.multicastEvent(new EnterpriseSyncEvent(enterprise, clientId));
        }

        User user = new User();
        user.setId(notifyForm.getUserId());
        user.setAccount(notifyForm.getAccount());
        user.setPassword(notifyForm.getPassword());
        userRepository.save(user);
        //发送用户信息
        applicationEventMulticaster.multicastEvent(new UserSyncEvent(user, clientId));

        return user;
    }

    @SneakyThrows
    public void notifyByDb(String clientId) {
        List<NotifyData> list = notifyDataRepository.findAll();


        int i = 0;
        for (NotifyData notifyData : list) {
            i++;
            if(i%10==0){
                log.info("同步暂停10秒钟，等待回收线程！");
                Thread.sleep(10000L);
            }
            Enterprise enterprise = enterpriseRepository.findOne(notifyData.getEnterpriseId());
            if (enterprise == null) {
                enterprise = new Enterprise();
            }
            enterprise.setId(notifyData.getEnterpriseId());
            enterprise.setName(notifyData.getEnterpriseName());
            enterpriseRepository.save(enterprise);
            //发送企业信息
            applicationEventMulticaster.multicastEvent(new EnterpriseSyncEvent(enterprise, clientId));

            User user = userRepository.findOne(notifyData.getUserId());
            if (user == null)
                user = new User();
            user.setId(notifyData.getUserId());
            user.setAccount(notifyData.getAccount());
            user.setPassword(notifyData.getPassword());
            user.setEnterpriseId(notifyData.getEnterpriseId());
            userRepository.save(user);
            //发送用户信息
            applicationEventMulticaster.multicastEvent(new UserSyncEvent(user, clientId));
        }
    }
}
