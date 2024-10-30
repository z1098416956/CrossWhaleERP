package com.neton.service;

import com.neton.config.MD5PasswordEncoder;
import com.neton.dto.CustomUserDetails;
import com.neton.entity.NetonUserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author TheSunshine
 * @date 2024-10-25 11:34:16
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private MD5PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO 这里需要实现查询用户 此处用md5加密了 正式环境需要BCrypt
        NetonUserDO netonUserDO = new NetonUserDO();
        netonUserDO.setUserName("admin");
        netonUserDO.setRole("ROLE_USER");
        netonUserDO.setPassword(passwordEncoder.encode("123456"));
        CustomUserDetails customUserDetails = new CustomUserDetails(netonUserDO);
        return customUserDetails;
    }
}
