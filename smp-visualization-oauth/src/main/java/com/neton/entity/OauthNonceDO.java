package com.neton.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author TheSunshine
 * @date 2024-10-24 11:50:57
 */
@Data
@TableName("oauth_nonce")
public class OauthNonceDO {
    /**
     * 非重复值
     */
    private String nonce;
    /**
     *过期时间
     */
    private Date expiration;
}
