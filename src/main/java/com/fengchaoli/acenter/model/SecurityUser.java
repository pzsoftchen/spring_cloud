package com.fengchaoli.acenter.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class SecurityUser extends org.springframework.security.core.userdetails.User {

    private String userId;

    public SecurityUser(User user, Boolean enabled,Boolean accountNonExpired, Boolean credentialsNonExpired ,Boolean accountNonLocked,Collection<GrantedAuthority> authorities){
        super(user.getAccount(),user.getPassword(),enabled,accountNonExpired,credentialsNonExpired ,accountNonLocked,authorities);
        this.userId = user.getId();
    }
}
