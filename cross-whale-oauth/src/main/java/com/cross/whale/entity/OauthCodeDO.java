package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author TheSunshine
 * @date 2024-10-24 11:47:17
 */
@Data
@TableName("oauth_code")
public class OauthCodeDO {
    /**
     * 授权码
     */
    private String code;
    /**
     *认证信息
     */
    private String authentication;
    /**
     *过期时间
     */
    private Date expiresAt;
}
