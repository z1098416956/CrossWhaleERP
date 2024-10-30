package com.neton.service;

import com.neton.config.MD5PasswordEncoder;
import com.neton.entity.AccAccountDO;
import com.neton.entity.NetonUserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        AccAccountDO netonUserDO = new AccAccountDO();
        netonUserDO.setAccountName("admin");
        netonUserDO.setEnabled(true);
       // netonUserDO.setAuthorities("ROLE_USER");
        netonUserDO.setAccountPassword(passwordEncoder.encode("123456"));
     //   CustomUserDetails customUserDetails = new CustomUserDetails("ROLE_USER",passwordEncoder.encode("123456"),"admin");
        return netonUserDO;
    }
}
