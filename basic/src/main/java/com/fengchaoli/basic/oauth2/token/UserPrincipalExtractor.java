package com.fengchaoli.basic.oauth2.token;

import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedPrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import java.util.Map;

public class UserPrincipalExtractor implements PrincipalExtractor {

    private FixedPrincipalExtractor fixedPrincipalExtractor = new FixedPrincipalExtractor();

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        Map<String, Object> authentication = (Map<String, Object>) map.get("userAuthentication");
        if (authentication != null) {
            Object principal =authentication.get("principal");
//            SecurityUser user = new SecurityUser();
//            try {
//                BeanUtils.copyProperties(user,  (Map<String, Object>) principal);
//            } catch (Exception e) {
//                throw new InvalidTokenException("");
//            }
//            return user;
            return null;
        } else {
            Object principal = this.fixedPrincipalExtractor.extractPrincipal(map);
            return (principal == null ? "unknown" : principal);
        }
    }
}
