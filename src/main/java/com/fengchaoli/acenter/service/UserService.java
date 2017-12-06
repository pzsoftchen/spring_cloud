package com.fengchaoli.acenter.service;

import com.fengchaoli.acenter.event.register.UserSyncEvent;
import com.fengchaoli.acenter.form.UserForm;
import com.fengchaoli.acenter.model.User;
import com.fengchaoli.acenter.model.UserMeta;
import com.fengchaoli.acenter.repository.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserService {

    @Autowired
    private ApplicationEventMulticaster applicationEventMulticaster;

    @Autowired
    private UserRepository userRepository;

    public User save(UserForm userForm,String clientId){
        User user = new User();
        user.setAccount(userForm.getAccount());
        user.setPassword(userForm.getPassword());

        UserMeta userMeta = new UserMeta();
        userMeta.setClientId(clientId);
        userMeta.setUser(user);
        userMeta.setExtra(userForm.getExtra());
        user.getUserMetas().add(userMeta);

        ///userRepository.save(user);
        applicationEventMulticaster.multicastEvent(new UserSyncEvent(user,clientId));

        return user;
    }
}
