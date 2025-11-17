package com.cross.whale.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author TheSunshine
 * @date 2024-10-25 10:52:26
 */
@Component
public class MD5PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        try {
            /*MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(rawPassword.toString().getBytes("UTF-8"));
            String pass = new String(Hex.encode(digest));*/
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
            String pass =bCryptPasswordEncoder.encode(rawPassword);
            return pass;
        } catch (Exception e) {
            throw new RuntimeException("Failed to encode password.", e);
        }
    }
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        //bCryptPasswordEncoder.matches(rawPassword,encodedPassword);
        return bCryptPasswordEncoder.matches(rawPassword,encodedPassword);
    }
}
