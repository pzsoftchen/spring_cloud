package com.fengchaoli.basic.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import java.util.Collection;

@Data
public class SecurityUser extends org.springframework.security.core.userdetails.User implements SocialUserDetails {

    private String userId;

    public SecurityUser(String userId, String account, String password, Boolean enabled, Boolean accountNonExpired, Boolean credentialsNonExpired, Boolean accountNonLocked, Collection<GrantedAuthority> authorities) {
        super(account, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }
}
