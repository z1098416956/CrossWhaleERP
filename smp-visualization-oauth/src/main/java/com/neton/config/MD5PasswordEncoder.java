package com.neton.config;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

/**
 * @author TheSunshine
 * @date 2024-10-25 10:52:26
 */
@Component
public class MD5PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(rawPassword.toString().getBytes("UTF-8"));
            String pass = new String(Hex.encode(digest));
            return pass;
        } catch (Exception e) {
            throw new RuntimeException("Failed to encode password.", e);
        }
    }
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(encode(rawPassword));
    }
}
