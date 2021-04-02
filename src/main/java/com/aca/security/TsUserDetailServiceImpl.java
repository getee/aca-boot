package com.aca.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.aca.user.entity.User;
import com.aca.user.service.UserService;

/**
 * 认证类。
 * @author lansheng
 */
@Component
public class TsUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        return org.springframework.security.core.userdetails.User.builder().username(user.getLoginName())
            .password(user.getPassword()).authorities(user.getRole().getName()).build();
    }

}
