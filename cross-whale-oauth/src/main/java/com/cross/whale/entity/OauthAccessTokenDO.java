package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author TheSunshine
 * @date 2024-10-24 11:45:41
 */
@Data
@TableName("oauth_access_token")
public class OauthAccessTokenDO {
    /**
     * 令牌ID
     */
    private String tokenId;
    /**
     *令牌数据
     */
    private String token;
    /**
     *认证ID
     */
    private String authenticationId;
    /**
     *用户名
     */
    private String userName;
    /**
     *客户端ID
     */
    private String clientId;
    /**
     *认证信息
     */
    private String authentication;
    /**
     *关联的刷新令牌
     */
    private String refreshToken;
    /**
     *创建时间
     */
    private Date createdAt;
}
