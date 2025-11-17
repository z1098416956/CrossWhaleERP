package com.cross.whale.service;

import com.cross.whale.config.MD5PasswordEncoder;
import com.cross.whale.dao.AccAccountDao;
import com.cross.whale.entity.AccAccountDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TheSunshine
 * @date 2024-10-25 11:34:16
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private MD5PasswordEncoder passwordEncoder;

    @Autowired
    private AccAccountDao accAccountDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<AccAccountDO> accAccountDOS = accAccountDao.queryAccountInfo(username);
        if (accAccountDOS == null || accAccountDOS.isEmpty()){
            throw new UsernameNotFoundException("用户不存在");
        }
        AccAccountDO accAccountDO = accAccountDOS.get(0);
        return accAccountDO;
    }
}
