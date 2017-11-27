package com.fengchaoli.acenter.service;

import com.fengchaoli.acenter.model.Role;
import com.fengchaoli.acenter.model.SecurityUser;
import com.fengchaoli.acenter.model.User;
import com.fengchaoli.acenter.repository.RoleRepository;
import com.fengchaoli.acenter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String account) {
        User user = userRepository.findFirstByEmail(account);

        return new SecurityUser(user,
                true, true, true, true,
                getAuthorities(roleRepository.findAll()));

    }

    private Collection<GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    /**
     * 获取角色
     */
    private List<String> getPrivileges(Collection<Role> roles) {
        return roles.stream()
                .map(role -> "ROLE_" + role.getName())
                .collect(Collectors.toList());
    }

    /**
     * 获取权限
     */
    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        return privileges.stream()
                .map(privilege -> new SimpleGrantedAuthority(privilege))
                .collect(Collectors.toList());
    }

}
