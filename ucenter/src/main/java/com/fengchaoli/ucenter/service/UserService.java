package com.fengchaoli.ucenter.service;

import com.fengchaoli.ucenter.event.sync.user.UserSyncEvent;
import com.fengchaoli.ucenter.form.UserForm;
import com.fengchaoli.ucenter.model.User;
import com.fengchaoli.ucenter.model.UserMeta;
import com.fengchaoli.ucenter.repository.NotifyDataRepository;
import com.fengchaoli.ucenter.repository.UserRepository;
import com.xiaoleilu.hutool.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
@Slf4j
public class UserService {

    @Autowired
    private ApplicationEventMulticaster applicationEventMulticaster;

    @Autowired
    private UserRepository userRepository;

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
}
