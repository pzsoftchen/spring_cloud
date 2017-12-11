package com.fengchaoli.acenter.service;

import com.fengchaoli.acenter.event.sync.user.UserSyncEvent;
import com.fengchaoli.acenter.form.UserForm;
import com.fengchaoli.acenter.model.User;
import com.fengchaoli.acenter.model.UserMeta;
import com.fengchaoli.acenter.repository.UserRepository;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class UserService {

    @Autowired
    private ApplicationEventMulticaster applicationEventMulticaster;

    @Autowired
    private UserRepository userRepository;

    public User getOne(String id){
        return userRepository.getOne(id);
    }

    public User insert(UserForm userForm,String clientId){
        User user = new User();
        user.setAccount(userForm.getAccount());
        user.setPassword(userForm.getPassword());
        user.setEnterpriseId(userForm.getEnterpriseId());

        UserMeta userMeta = new UserMeta();
        userMeta.setClientId(clientId);
        userMeta.setExtra(userForm.getExtra());
        user.getUserMetas().add(userMeta);

        userRepository.save(user);
        applicationEventMulticaster.multicastEvent(new UserSyncEvent(user,clientId));
        return user;
    }

    public User update(String id,UserForm userForm,String clientId){
        User user = userRepository.getOne(id);
        UserMeta userMeta = user.getUserMetas().stream().filter(meta ->
                ObjectUtil.equal(clientId,meta.getClientId())).findFirst().get();
        if(userMeta==null){
            userMeta = new UserMeta();
            userMeta.setClientId(clientId);
            userMeta.setExtra(userForm.getExtra());
            user.getUserMetas().add(userMeta);
        }else{
            userMeta.setExtra(userForm.getExtra());
        }

        userRepository.save(user);
        applicationEventMulticaster.multicastEvent(new UserSyncEvent(user,clientId));
        return user;
    }
}
